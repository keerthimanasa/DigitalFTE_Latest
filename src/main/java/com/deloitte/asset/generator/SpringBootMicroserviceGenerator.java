package com.deloitte.asset.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.deloitte.asset.util.CodgenUtil;
import com.deloitte.asset.vo.CodeGeneratorRequest;
import com.deloitte.asset.vo.DepedencyDetails;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public class SpringBootMicroserviceGenerator {

	@Autowired
	private Configuration cfg;
	
	@Autowired
	ResourceLoader resourceLoader;

	@Value("${file.temp.store}")
	private String genratedTempPath;

	@Value("${java.app.serviceclassname}")
	private String serviceClassName;

	@Value("${java.app.controllername}")
	private String controllerClassName;

	@Value("${java.app.interfacename}")
	private String interfaceName;

	@Value("${java.app.propertyfilename}")
	private String propertyFileName;
	
	@Value("${java.app.serviceinterface}")
	private String serviceInterfaceName;

	Map<String, Object> dataMap = new HashMap<>();
	
	@Autowired
	public Map<String,DepedencyDetails> depedencyList;

	public void buildData(CodeGeneratorRequest request) throws Exception {
		dataMap.put("package", request.getPackageName());
		dataMap.put("bootVersion", request.getBootVersion());
		dataMap.put("groupId", request.getGroupId());
		dataMap.put("artifactId", request.getArtifactId());
		dataMap.put("name", request.getName());
		dataMap.put("description", request.getDescription());
		dataMap.put("className", request.getName());
		List<DepedencyDetails> finaldepedencyList = new ArrayList<>();
		
		for(String dependency : request.getDependencies()) {
			System.out.println("dpndcy adding="+dependency);
			if(depedencyList.containsKey(dependency)) {
				
				finaldepedencyList.add(depedencyList.get(dependency));
			}else {
				throw new Exception("Depedency Added is not supported as of now");
			}
		}
		dataMap.put("dependencyList", finaldepedencyList);

		// writeFile(className);
	}

	public void genrateProject(String appName, CodeGeneratorRequest request)
			throws Exception {

		// Create DataMap
		buildData(request);
		// Create Base Project Structure
		createBaseProjectFolderStructure(request.getName());

		Template template = null;
		Resource resource=resourceLoader.getResource("classpath:/templates/springboot");
		File[] files = resource.getFile().listFiles();
		for (File file : files) {
			if (!file.isDirectory()) {
				template = cfg.getTemplate(file.getName());
				if (file.getName().equalsIgnoreCase("PomBuilder.ftl")) {
					// Props File generator
					String pomFilePath= genratedTempPath + "/" + request.getName();
					writePomFile(template,pomFilePath);

				} else if (file.getName().equalsIgnoreCase("PropertyFileBuilder.ftl")) {
					// Props File Generator
					String propsFilePath= genratedTempPath + "/" + request.getName()+"/src/main/resources";
					writePropertyFile(template,propsFilePath);

				} else {
					// Java Class
					writeJavaFiles(template,CodgenUtil.getFileNamewithoutExtension(file.getName()) ,
							genratedTempPath + "/" + request.getName() + "/src/main/java/" + CodgenUtil.packageToPath(request.getPackageName()));
				}

			}
		}

	}

	public Boolean createBaseProjectFolderStructure(String appName) throws IOException {
		Boolean status = false;
		try {
			Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
	        //add owners permission
	        perms.add(PosixFilePermission.OWNER_READ);
	        perms.add(PosixFilePermission.OWNER_WRITE);
	        perms.add(PosixFilePermission.OWNER_EXECUTE);
	        //add group permissions
	        perms.add(PosixFilePermission.GROUP_READ);
	        perms.add(PosixFilePermission.GROUP_WRITE);
	        perms.add(PosixFilePermission.GROUP_EXECUTE);
	        //add others permissions
	        perms.add(PosixFilePermission.OTHERS_READ);
	        perms.add(PosixFilePermission.OTHERS_WRITE);
	        perms.add(PosixFilePermission.OTHERS_EXECUTE);

	        FileAttribute<?> permission = PosixFilePermissions.asFileAttribute(perms);
			// Create Project directory
			Files.createDirectories(Paths.get(genratedTempPath + "/" + appName),permission);
			// Create src and Test Directory
			Files.createDirectories(Paths.get(genratedTempPath + "/" + appName + "/src/main/java"),permission);
			Files.createDirectories(Paths.get(genratedTempPath + "/" + appName + "/src/test/java"),permission);
			// Resource
			Files.createDirectories(Paths.get(genratedTempPath + "/" + appName + "/src/main/resources"),permission);
			Files.createDirectories(Paths.get(genratedTempPath + "/" + appName + "/src/test/resources"),permission);
			status = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = false;
		}

		return status;

	}

	public void writeJavaFiles(Template template, String className, String path) {
		try {
			File file = null;
			if (className.contains("Controller")) {
				file = new File(path + "/controller" + "/" + controllerClassName + ".java");
				dataMap.put("controller_ClassName", controllerClassName);

			} else if (className.contains("ServiceImpl")) {
				file = new File(path + "/service" + "/" + serviceClassName + ".java");
				dataMap.put("service_ClassName", serviceClassName);
			} else if (className.contains("Service")) {
				file = new File(path + "/service" + "/" + serviceInterfaceName + ".java");
				dataMap.put("interface_Name", serviceInterfaceName);
			} else if (className.contains("Config")) {
				file = new File(path + "/config" + "/" + className + ".java");
			} else {
				file = new File(path + "/" + CodgenUtil.toCamelCase(className) + ".java");
				dataMap.put("main_className", CodgenUtil.toCamelCase(className));
			}
			Files.createDirectories(Paths.get(file.getPath()).getParent());
			Writer writer = new FileWriter(file);
			template.process(dataMap, writer);
			writer.flush();
			writer.close();
			System.out.println("Success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writePomFile(Template template, String path) {
		try {
			File file = new File(path + "/pom.xml");
			//Files.createDirectories(Paths.get(path + "/pom.xml"));
			Writer writer = new FileWriter(file);
			template.process(dataMap, writer);
			writer.flush();
			writer.close();
			System.out.println("POM FIle :Success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writePropertyFile(Template template, String propsFilePath) {
		try {
			File file = new File(propsFilePath + "/application.properties");
			//Files.createDirectories(Paths.get(path + "/pom.xml"));
			Writer writer = new FileWriter(file);
			template.process(dataMap, writer);
			writer.flush();
			writer.close();
			System.out.println(" Property File : Success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

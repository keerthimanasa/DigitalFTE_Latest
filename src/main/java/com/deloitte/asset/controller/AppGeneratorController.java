package com.deloitte.asset.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.asset.service.JavaCodeGenerator;
import com.deloitte.asset.vo.CodeGeneratorRequest;

@RestController
public class AppGeneratorController {

	@Autowired
	private JavaCodeGenerator javaCodeGenerator;

	@Value("${file.temp.store}")
	private String genratedTempPath;
	
	@Value("${app.default.depedencies:swagger,swaggerui}")
	private List<String> defaultDepedency; 
	
	@GetMapping("/spring-micro")
	public void genSpringBootApp(@RequestParam(name="type",required=false) String type, @RequestParam(name="language",required=false) String language,
			@RequestParam(name="bootVersion",required=false) String bootVersion, @RequestParam(name="baseDir",required=false) String baseDir,
			@RequestParam(name="groupId",required=false) String groupId, // com.deloitte.asset
			@RequestParam(name="artifactId",required=false) String artifactId, // BootstrapApp
			@RequestParam(name="name",required=false) String name, // BootstrapApp
			@RequestParam(name="description",required=false) String description, // Demo%20project%20for%20Spring%20Boot
			@RequestParam(name="packageName",required=false) String packageName, // com.deloitte.asset.BootstrapApp
			@RequestParam(name="packaging",required=false) String packaging, @RequestParam(name="javaVersion",required=false) String javaVersion, // 1.8
			@RequestParam(name="dependencies",required=false) String[] dependencies, HttpServletResponse response) throws IOException {
		try {
			CodeGeneratorRequest request = new CodeGeneratorRequest();
			request.setName(name);
			request.setArtifactId(artifactId);
			request.setGroupId(groupId);
			request.setBaseDir(baseDir);
			request.setBootVersion(bootVersion);
			request.setDescription(description);
			request.setJavaVersion(javaVersion);
			request.setLanguage(language);
			request.setPackageName(packageName);
			request.setPackaging(packaging);
			request.setType(type);
			Set<String> depedencyArrList = new HashSet<String>();
			depedencyArrList.addAll(Arrays.asList(dependencies));
			//Add default dependencies
			if(!defaultDepedency.isEmpty()) {
				depedencyArrList.addAll(defaultDepedency);
			}
			System.out.println("Depedency List="+depedencyArrList);
			request.setDependencies(depedencyArrList);
			
			javaCodeGenerator.genarateFile(packageName,request);

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + artifactId + ".zip");
			response.setStatus(HttpServletResponse.SC_OK);

			List<String> fileNames = getFiles(genratedTempPath);

			System.out.println("############# file size ###########" + fileNames.size());

			try (ZipOutputStream zippedOut = new ZipOutputStream(response.getOutputStream())) {
				for (String file : fileNames) {
					FileSystemResource resource = new FileSystemResource(file);
					String filePath = resource.getFile().getPath().substring((genratedTempPath+"/"+artifactId+"/").length(),resource.getFile().getPath().length() );
					System.out.println("Fiel Path ="+ filePath);
					ZipEntry e = new ZipEntry(filePath);
					// Configure the zip entry, the properties of the file
					e.setSize(resource.contentLength());
					e.setTime(System.currentTimeMillis());
					// etc.
					zippedOut.putNextEntry(e);
					// And the content of the resource:
					StreamUtils.copy(resource.getInputStream(), zippedOut);
					zippedOut.closeEntry();
				}
				zippedOut.finish();
			} catch (Exception e) {
				// Exception handling goes here
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			//remove the temp folder
			deleteDirectory(Paths.get(genratedTempPath).toFile());
			
		}

	}

	public List<String> getFiles(String location) {
		List<String> result = null;
		try (Stream<Path> walk = Files.walk(Paths.get(location + "//"))) {

			result = walk.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean deleteDirectory(File directoryToBeDeleted) {
		File[] allContents = directoryToBeDeleted.listFiles();
		if (allContents != null) {
			for (File file : allContents) {
				deleteDirectory(file);
			}
		}
		return directoryToBeDeleted.delete();
	}
	
}

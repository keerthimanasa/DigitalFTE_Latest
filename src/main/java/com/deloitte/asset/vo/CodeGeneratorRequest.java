package com.deloitte.asset.vo;

import java.util.List;
import java.util.Set;

public class CodeGeneratorRequest {
	private String type;
	private String language;
	private String bootVersion;
	private String baseDir;
	private String groupId; //com.deloitte.asset
	private String artifactId;//BootstrapApp
	private String name; //BootstrapApp
	private String description; //Demo%20project%20for%20Spring%20Boot
	private String packageName; //com.deloitte.asset.BootstrapApp
	private String packaging;
	private String javaVersion;//1.8
	private Set<String> dependencies;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getBootVersion() {
		return bootVersion;
	}
	public void setBootVersion(String bootVersion) {
		this.bootVersion = bootVersion;
	}
	public String getBaseDir() {
		return baseDir;
	}
	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getArtifactId() {
		return artifactId;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackaging() {
		return packaging;
	}
	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
	public String getJavaVersion() {
		return javaVersion;
	}
	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}
	public Set<String> getDependencies() {
		return dependencies;
	}
	public void setDependencies(Set<String> dependencies) {
		this.dependencies = dependencies;
	}
	
}

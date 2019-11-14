package com.deloitte.asset.model;

import java.util.ArrayList;

public class JavaProject {
	
	private String language; 
	private String project;
	private String version;
	private String baseDir;
	private String groupId;

	private String artifactId;

	private String projectName;

	private String projectDesc;

	private String packageName;

	private String packaging;

	private String javaVersion;
	
	private ArrayList<String> dependencies;
	
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
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
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
	public ArrayList<String> getDependencies() {
		return dependencies;
	}
	public void setDependencies(ArrayList<String> dependencies) {
		this.dependencies = dependencies;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "JavaProject [language=" + language + ", project=" + project + ", version=" + version + ", baseDir="
				+ baseDir + ", groupId=" + groupId + ", artifactId=" + artifactId + ", projectName=" + projectName
				+ ", projectDesc=" + projectDesc + ", packageName=" + packageName + ", packaging=" + packaging
				+ ", javaVersion=" + javaVersion + ", dependencies=" + dependencies + "]";
	}
	
}

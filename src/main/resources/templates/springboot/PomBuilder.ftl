<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>${bootVersion}</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>${groupId}</groupId>
	<artifactId>${artifactId}</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>${name}</name>
	<description>${description}</description>

	<properties>
		<java.version>1.8</java.version>
		<spring-cloud.version>Hoxton.RC1</spring-cloud.version>
	</properties>

	<dependencies>
	<#list dependencyList as DepedencyDetails> 
		<dependency>
			<groupId>${DepedencyDetails.groupId}</groupId>
			<artifactId>${DepedencyDetails.artifactId}</artifactId>
			<#if DepedencyDetails.version ??>
			<version>${DepedencyDetails.version}</version>
			</#if>
			<#if DepedencyDetails.scope ??>
			<scope>${DepedencyDetails.scope}</scope>
			</#if>
		</dependency>
	</#list>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework.amqp</groupId>
			<artifactId>spring-rabbit-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${r"${spring-cloud.version}"}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

	<repositories>
		<repository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
		</repository>
	</repositories>

</project>
package com.deloitte.asset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.asset.generator.SpringBootMicroserviceGenerator;
import com.deloitte.asset.vo.CodeGeneratorRequest;

@Service
public class JavaCodeGenerator {

	@Autowired
	private SpringBootMicroserviceGenerator springBootMicroservice;

	public Boolean genarateFile(String packageName, CodeGeneratorRequest request) {
		try {
		springBootMicroservice.genrateProject(packageName,request);
		return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}

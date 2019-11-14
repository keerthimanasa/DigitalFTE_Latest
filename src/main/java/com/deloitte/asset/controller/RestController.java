package com.deloitte.asset.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.asset.model.JavaProject;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	 @RequestMapping(value="/javaproject",method = RequestMethod.GET)
	 @ResponseBody
	  public JavaProject test(/*@RequestBody JavaProject jp*/) {
		 
		 JavaProject jp = new JavaProject();
		 jp.setLanguage("java");
		  System.out.println("Java Project inputs ==> ");
		  return jp;
	  }

	 
	 @RequestMapping(value="/java", method=RequestMethod.POST)
	 @ResponseBody
	 public JavaProject java(@RequestBody JavaProject jp) {
 
		 System.out.println("data ==> "+ jp.getLanguage());
		 System.out.println("dependencies ==> "+ jp.getDependencies());
		 System.out.println("input payload " + jp.toString());
		 
		 return jp;
	 }
}

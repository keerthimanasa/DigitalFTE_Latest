package com.deloitte.asset.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(ignoreUnknownFields = true, prefix = "java.app")
public class JavaAppConfig {
	private String serviceclassname;
	private String interfacename;
	private String controllername;
	private String propertyfilename;

	public String getServiceclassname() {
		return serviceclassname;
	}

	public void setServiceclassname(String serviceclassname) {
		this.serviceclassname = serviceclassname;
	}

	public String getInterfacename() {
		return interfacename;
	}

	public void setInterfacename(String interfacename) {
		this.interfacename = interfacename;
	}

	public String getControllername() {
		return controllername;
	}

	public void setControllername(String controllername) {
		this.controllername = controllername;
	}

	public String getPropertyfilename() {
		return propertyfilename;
	}

	public void setPropertyfilename(String propertyfilename) {
		this.propertyfilename = propertyfilename;
	}

}

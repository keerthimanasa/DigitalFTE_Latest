package com.deloitte.asset.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.util.StringUtils;

import com.deloitte.asset.vo.DepedencyDetails;

@Configuration
public class DepedencyConfig {

	@Autowired
	Environment environment;

	@Bean
	public Map<String,DepedencyDetails> depedencyList() {
		Map<String,DepedencyDetails> rtn = new HashMap<>();
		if (environment instanceof ConfigurableEnvironment) {
			for (PropertySource<?> propertySource : ((ConfigurableEnvironment) environment).getPropertySources()) {
				if (propertySource instanceof EnumerablePropertySource) {
					for (String key : ((EnumerablePropertySource) propertySource).getPropertyNames()) {
						if(key.startsWith("depedency")) {
							String baseProp = key.substring(0,key.lastIndexOf("."));
							DepedencyDetails dt = new DepedencyDetails();
							if(key.endsWith("groupId") && propertySource.getProperty(key) != null) {
								dt.setGroupId(propertySource.getProperty(key).toString());
								dt.setArtifactId(propertySource.getProperty(baseProp+".artifactId").toString());
								if(propertySource.getProperty(baseProp+".version") != null)
								dt.setVersion(propertySource.getProperty(baseProp+".version").toString());
								if(propertySource.getProperty(baseProp+".scope") != null)
								dt.setScope(propertySource.getProperty(baseProp+".scope").toString());
							System.out.println("vo="+dt);
							rtn.put(getMapKeyName(key), dt);
							}
						}
					}
				}
			}
		}
		return rtn;
	}
	
	public String getMapKeyName(String keyName) {
		String name = null;
		if(StringUtils.hasText(keyName)) {
			String[] arr = keyName.split("\\.");
			name = arr[1];
		}
		return name;
	}
	
	public String getArtifactId(String keyName) {
		String name = null;
		if(StringUtils.hasText(keyName)) {
			String[] arr = keyName.split("\\.");
			name = arr[1];
		}
		return name;
	}

}

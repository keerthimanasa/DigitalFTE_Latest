package ${package}.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 
 * @author <name>
 *
 */
@Service
public class ${service_ClassName} implements ${interface_Name} {

	private static final Logger logger = LoggerFactory.getLogger(${service_ClassName}.class);

	@Override
	public String fetchPingText(){
		
			return "Your server is up and running at port: Service";
	}
}
package ${package}.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class ${controller_ClassName} {

@Autowired
private Environment environment;

private static final Logger logger = LoggerFactory.getLogger(${controller_ClassName}.class);
    @RequestMapping("/ping")
    public String ping() {
    	
         return "Your server is up and running at port: "+environment.getProperty("local.server.port"); 
    }

}
package com.proyectosj4sas.app.security;

import org.hibernate.cfg.Environment;
public class AppProperties {
	//@Autowired
	//private Environment env;
	
	public String getTokenSecret()
	{
		return Environment.getProperties().getProperty("tokenSecret");
	}
}

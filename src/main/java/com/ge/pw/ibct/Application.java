package com.ge.pw.ibct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan({"com.ge.pw.ibct.services","com.ge.pw.ibct.controllers"})
@EnableAutoConfiguration
@EntityScan(basePackages=("com.ge.pw.ibct.entity"))
@EnableJpaRepositories("com.ge.pw.ibct.repository")
@CrossOrigin(origins = "http://localhost:8080")
public class Application extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}
	
    public static void main(String[] args) {
    	Object[] obj = {Application.class};
    	SpringApplication.run(obj,args);
    }
}


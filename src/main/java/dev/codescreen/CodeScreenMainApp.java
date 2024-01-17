package dev.codescreen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = "dev.codescreen")
@ConfigurationPropertiesScan("dev.codescreen.properties")
public class CodeScreenMainApp {
	
	public static void main(String[]args) {
		
		SpringApplication.run(CodeScreenMainApp.class, args);
		
	}

}

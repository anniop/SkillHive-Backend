package com.amdocs.skillhive.skillhive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.amdocs.skillhive")
public class SkillhiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillhiveApplication.class, args);
	}

}

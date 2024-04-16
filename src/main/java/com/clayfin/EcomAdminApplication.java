package com.clayfin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.clayfin","com.clayfin.util"})
public class EcomAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomAdminApplication.class, args);
	}

}

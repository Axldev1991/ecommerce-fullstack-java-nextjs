package com.ecommerce.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the E-commerce API.
 * The {@link SpringBootApplication} annotation enables:
 * - Component Scanning (finds @Service, @RestController, etc.)
 * - Auto-configuration (Spring sets up defaults based on dependencies)
 * - Configuration loading
 */
@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}

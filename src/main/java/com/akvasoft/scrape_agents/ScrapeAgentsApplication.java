package com.akvasoft.scrape_agents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ScrapeAgentsApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ScrapeAgentsApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ScrapeAgentsApplication.class, args);
	}
}

package br.bionexo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@ComponentScan(basePackages = { "br.bionexo.api" })
@EnableJpaAuditing
public class ApiApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}

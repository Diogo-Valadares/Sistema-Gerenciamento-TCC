package com.GerenciadorTCC.controller.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({	
	"com.GerenciadorTCC.controller.assembler",
	"com.GerenciadorTCC.controller.apirest",
	"com.GerenciadorTCC.controller.exception",
	"com.GerenciadorTCC.controller.view",
	"com.GerenciadorTCC.service",
	"com.GerenciadorTCC"}
)
@EntityScan("com.GerenciadorTCC.entities")
@EnableJpaRepositories("com.GerenciadorTCC.repository")
public class GerenciadorTccApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(GerenciadorTccApplication.class, args);
	}
}
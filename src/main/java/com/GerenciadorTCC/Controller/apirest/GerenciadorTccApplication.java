package com.GerenciadorTCC.Controller.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({
	"com.GerenciadorTCC.Controller.apirest",
	"com.GerenciadorTCC.Controller.view",
	"com.GerenciadorTCC.Service",
	"com.GerenciadorTCC.Repository"}
)
public class GerenciadorTccApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorTccApplication.class, args);
	}

}

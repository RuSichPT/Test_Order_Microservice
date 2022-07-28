package com.github.RuSichPT.TestOrderMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class TestOrderMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestOrderMicroserviceApplication.class, args);
	}

}

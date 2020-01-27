package com.nitv;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeviceCrudApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DeviceCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("My name is as follows");
	}
}

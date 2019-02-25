package com.example.backservice;

import com.example.backservice.starter.StartTaskAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BackserviceApplication implements CommandLineRunner{

	@Autowired
	private StartTaskAsync startTaskAsync;

	public static void main(String[] args) {
		SpringApplication.run(BackserviceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		startTaskAsync.run();
	}
}

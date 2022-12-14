package com.carretas.carretas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		//SpringApplication.run(Application.class, args);

        try {
            SpringApplication app = new SpringApplication(Application.class);
            app.run(args);
        } catch(Throwable ex) {
            ex.printStackTrace();
        }
	}
}
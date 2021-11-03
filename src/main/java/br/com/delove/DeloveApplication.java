package br.com.delove;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DeloveApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeloveApplication.class, args);
	}

}

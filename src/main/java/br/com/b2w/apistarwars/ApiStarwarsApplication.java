package br.com.b2w.apistarwars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ApiStarwarsApplication  {

	
	public static void main(String[] args) {

		SpringApplication.run(ApiStarwarsApplication.class, args);
	}


}

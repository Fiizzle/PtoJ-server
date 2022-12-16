package com.example.Fiizzle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class FiizzleApplication {
	//	public static final String APPLICATION_LOCATIONS = "spring.config.location="
//			+ "classpath:application.yml,";
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml";

	public static void main(String[] args) {

		SpringApplication.run(FiizzleApplication.class, args);

//		new SpringApplicationBuilder(FiizzleApplication.class)
//				.properties(APPLICATION_LOCATIONS)
//				.run(args);

		// 메모리 사용량 출력
		long heapSize = Runtime.getRuntime().totalMemory();
		System.out.println("HEAP Size(M) : " + heapSize / (1024 * 1024) + " MB");
	}
}
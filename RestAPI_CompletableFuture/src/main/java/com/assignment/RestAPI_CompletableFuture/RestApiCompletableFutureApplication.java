package com.assignment.RestAPI_CompletableFuture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAspectJAutoProxy
public class RestApiCompletableFutureApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiCompletableFutureApplication.class, args);
	}

}

package com.jzw.user;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableDiscoveryClient
@EnableTransactionManagement
@SpringBootApplication(
		exclude = {
				DataSourceAutoConfiguration.class
		}
)
public class Application{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

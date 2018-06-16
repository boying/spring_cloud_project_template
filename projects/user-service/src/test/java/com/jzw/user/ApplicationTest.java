package com.jzw.user;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableDiscoveryClient
@EnableTransactionManagement
@SpringBootApplication(
		exclude = {
				DataSourceAutoConfiguration.class
		}
)
public class ApplicationTest {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationTest.class, args);
	}
}

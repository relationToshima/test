package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbTestApplication.class, args);
	}
/*
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);

		sessionFactory.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));

		return sessionFactory.getObject();
	}
*/
}

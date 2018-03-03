package com.imcs.spring.factory;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan("com.imcs.spring")
public class AppConfig {

	@Autowired
	Environment env;

	@Bean
	public Connection getMySqlDataSource() throws SQLException {
		BasicDataSource mysqlDS = null;
		Connection connection = null;
		try {
			mysqlDS = new BasicDataSource();

			mysqlDS.setDriverClassName(env.getProperty("DRIVER_CLASS"));
			mysqlDS.setUrl(env.getProperty("URL"));
			mysqlDS.setUsername(env.getProperty("USERNAME"));
			mysqlDS.setPassword(env.getProperty("PASSWORD"));
			connection = mysqlDS.getConnection();
			return connection;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mysqlDS.close();
		}
		return null;
	}
}

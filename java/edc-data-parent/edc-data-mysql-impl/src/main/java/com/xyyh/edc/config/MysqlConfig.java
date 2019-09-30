package com.xyyh.edc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MysqlConfig {
	
	@Primary
	@Bean(name = "primaryDataSource")
	@Qualifier("primaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "mysqlDataSource")
	@Qualifier("mysqlDataSource")
	@ConfigurationProperties(prefix = "spring.datasource2")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "mysqlJdbcTemplate")
	public JdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}

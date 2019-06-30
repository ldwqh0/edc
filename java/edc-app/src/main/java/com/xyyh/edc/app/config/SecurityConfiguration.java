package com.xyyh.edc.app.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(HttpSecurity security) throws Exception {
		security.authorizeRequests().antMatchers("/**").permitAll();
		security.csrf().disable();
	}
}

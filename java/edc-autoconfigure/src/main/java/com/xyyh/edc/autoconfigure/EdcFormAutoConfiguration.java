package com.xyyh.edc.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.xyyh.edc.form.entity.FormDefine;

@Configuration
@ConditionalOnClass(FormDefine.class)
@EntityScan("com.xyyh.edc.form")
@EnableJpaRepositories("com.xyyh.edc.form")
@ComponentScan("com.xyyh.edc.form")
public class EdcFormAutoConfiguration {

}

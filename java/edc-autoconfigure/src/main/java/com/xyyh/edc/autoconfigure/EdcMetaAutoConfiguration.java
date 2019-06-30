package com.xyyh.edc.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.xyyh.edc.meta.entity.Table;

@EntityScan({ "com.xyyh.edc.meta.entity" })
@EnableJpaRepositories({ "com.xyyh.edc.meta.repository" })
@ComponentScan({ "com.xyyh.edc.meta" })
@ConditionalOnClass(Table.class)
public class EdcMetaAutoConfiguration {

}

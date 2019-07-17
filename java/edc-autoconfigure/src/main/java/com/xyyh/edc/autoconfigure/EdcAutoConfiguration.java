package com.xyyh.edc.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.xyyh.edc.data.service.EdcDataService;

@ComponentScan(basePackages = { "com.xyyh.edc.data" })
@ConditionalOnClass(EdcDataService.class)
@Import(EdcDataMongoConfiguration.class)
public class EdcAutoConfiguration {

}

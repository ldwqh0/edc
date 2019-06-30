package com.xyyh.edc.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;

import com.xyyh.edc.data.service.EdcDataService;

@ComponentScan(basePackages = { "com.xyyh.edc.data" })
@ConditionalOnClass(EdcDataService.class)
public class EdcAutoConfiguration {

}

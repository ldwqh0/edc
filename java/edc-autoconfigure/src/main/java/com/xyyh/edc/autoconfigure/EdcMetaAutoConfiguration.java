package com.xyyh.edc.autoconfigure;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.xyyh.edc.meta.entity.Table;

@EntityScan({ "com.xyyh.edc.meta.entity" })
@EnableJpaRepositories({ "com.xyyh.edc.meta.repository" })
@ComponentScan({ "com.xyyh.edc.meta" })
@ConditionalOnClass(Table.class)
public class EdcMetaAutoConfiguration {

	@Configuration
	@ConditionalOnClass(ObjectId.class)
	class ObjectMapperConfig {
		@Autowired
		private ObjectMapper objectMapper;

		@PostConstruct
		public void configObjectMapper() {
			SimpleModule module = new SimpleModule("ObjectIdmodule");
			module.addSerializer(ObjectId.class, new JsonSerializer<ObjectId>() {
				@Override
				public void serialize(ObjectId value, JsonGenerator gen, SerializerProvider serializers)
						throws IOException {
					gen.writeString(value.toHexString());
				}
			});
			objectMapper.registerModule(module);
		};
	}

}

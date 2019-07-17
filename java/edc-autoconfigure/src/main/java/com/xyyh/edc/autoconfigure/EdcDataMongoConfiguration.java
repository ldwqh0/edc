package com.xyyh.edc.autoconfigure;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
@ConditionalOnClass(ObjectId.class)
public class EdcDataMongoConfiguration {
	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	public void configObjectMapper() {
		// 配置ObjectId的序列化方式
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

package com.xyyh.edc.app.config;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class Config {

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

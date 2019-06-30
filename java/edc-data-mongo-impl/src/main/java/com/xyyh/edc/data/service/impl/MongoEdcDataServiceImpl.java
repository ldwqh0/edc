package com.xyyh.edc.data.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.client.MongoClient;
import com.xyyh.edc.data.service.EdcDataService;
import com.xyyh.edc.meta.entity.Table;
import com.xyyh.edc.meta.service.TableService;

@Service
public class MongoEdcDataServiceImpl implements EdcDataService {

	@Autowired
	private TableService tableService;

//	private m

	@Autowired
	private MongoTemplate mongoTemplate;

	private MongoClient client;

	@Override
	@Transactional
	public Map<String, Object> save(Long id, Map<String, Object> data) {
		Optional<Table> table = tableService.findById(id);
		if (table.isPresent()) {
			data.put("_id", "asd");
			return mongoTemplate.save(data, table.get().getName());
		} else {
			return null;
		}

	}

}

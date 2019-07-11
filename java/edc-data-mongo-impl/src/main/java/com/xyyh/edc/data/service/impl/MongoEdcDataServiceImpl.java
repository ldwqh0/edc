package com.xyyh.edc.data.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.client.MongoClient;
import com.xyyh.edc.data.service.EdcDataService;
import com.xyyh.edc.meta.entity.Column;
import com.xyyh.edc.meta.entity.Table;
import com.xyyh.edc.meta.service.TableService;

@Service
public class MongoEdcDataServiceImpl implements EdcDataService {

	@Autowired
	private TableService tableService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Transactional
	public Map<String, Object> save(Long id, Map<String, Object> data) {
		Optional<Table> table = tableService.findById(id);
		// 主键的判定
		// 主键的映射
		// 唯一键的控制
		if (table.isPresent()) {
//			data.put("_id", "asd");
//			MongoClient
			return mongoTemplate.save(data, table.get().getName());
		} else {
			return null;
		}
	}

	@Override
	public Map<String, Object> save(String collection, Map<String, Object> data) {
		Optional<Table> table = tableService.findByName(collection);
		if (table.isPresent()) {
			return mongoTemplate.save(data, table.get().getName());
		} else {
			return null;
		}
	}

	@Override
	public Object findOne(String collection, Object dataId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(String collection, Object dataId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<Object> list(String collection, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Column> getIdColumns(Table table) {
		return table.getColumns().stream().filter(Column::isIdColumn).collect(Collectors.toList());
	}

}

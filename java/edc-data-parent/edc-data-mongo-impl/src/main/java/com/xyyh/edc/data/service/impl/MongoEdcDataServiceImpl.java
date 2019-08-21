package com.xyyh.edc.data.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.client.MongoClient;
import com.xyyh.edc.data.service.EdcDataService;
import com.xyyh.edc.meta.api.TableDefine;

import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;;

@Service
public class MongoEdcDataServiceImpl implements EdcDataService {

	@Autowired
	private MongoTemplate mongoTemplate;
	MongoClient c;

	@Override
	@Transactional
	public Map<String, Object> save(TableDefine table, String dataId, Map<String, Object> data) {
		data.put("_id", dataId);
		return mongoTemplate.save(data, table.getName());
	}

	@Override
	@Transactional
	public Map<String, Object> update(TableDefine table, String dataId, Map<String, Object> data) {
		Map<String, Object> exist = findOne(table, dataId);
		if (Objects.isNull(exist)) {
			// TODO 如果不存在
			return null;
		} else {
			if (ObjectId.isValid(dataId)) {
				data.put("_id", new ObjectId(dataId));
			} else {
				data.put("_id", dataId);
			}
			return mongoTemplate.save(data, table.getName());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findOne(final TableDefine table, final String dataId) {
		// 如果是_id是ObjectId的字符串的话，一定会查找为ObjectId
		return mongoTemplate.findOne(query(where("_id").is(dataId)), Map.class, table.getName());
	}

	@Override
	public void deleteById(TableDefine table, String dataId) {
		mongoTemplate.remove(query(where("_id").is(dataId)), table.getName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Map<String, Object>> list(TableDefine table, Pageable pageable) {
		Query query = new Query();
		long count = mongoTemplate.count(query, table.getName());
		query.skip(pageable.getOffset()).limit(pageable.getPageSize());
		@SuppressWarnings("rawtypes")
		List<Map> list = mongoTemplate.find(query, Map.class, table.getName());
		List<Map<String, Object>> r = new ArrayList<>();
		list.forEach(i -> r.add(i));
		// 以下代码会IDE便宜不会报错，但maven便宜报错
		// List<Map<String, Object>> r2 = list.stream().map(i ->
		// i).collect(Collectors.toList());
		return new PageImpl<Map<String, Object>>(r, pageable, count);
	}
}

package com.xyyh.edc.data.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyyh.edc.common.utils.StreamUtils;
import com.xyyh.edc.data.service.EdcDataService;
import com.xyyh.edc.meta.api.ColumnDefine;
import com.xyyh.edc.meta.api.TableDefine;

import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;;

@Service
public class MongoEdcDataServiceImpl implements EdcDataService {

//	@Autowired
//	private TableService tableService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	@Transactional
	public Map<String, Object> save(TableDefine table, Map<String, Object> data) {
		// 根据表元数据判断数据ID
		// 如果没有指定ID列，使用默认的ID列
		String dataId = getId(table, data);
		if (StringUtils.isNotBlank(dataId)) {
			data.put("_id", dataId);
		}
		return mongoTemplate.save(data, table.getName());
	}

	@Override
	public Object update(TableDefine table, String dataId, Map<String, Object> data) {
		Object exist = findOne(table, dataId);
		if (Objects.isNull(exist)) {
			// TODO 如果不存在
			return null;
		} else {
			if (ObjectId.isValid(dataId)) {
				data.put("_id", new ObjectId(dataId));
			} else {
				data.put("_id", data);
			}
			return mongoTemplate.save(data, table.getName());
		}
	}

	@Override
	public Object findOne(final TableDefine collection, final String dataId) {
		// 如果是_id是ObjectId的字符串的话，一定会查找为ObjectId
		return mongoTemplate.findOne(query(where("_id").is(dataId)), Map.class, collection.getName());
	}

	@Override
	public void deleteById(TableDefine collection, String dataId) {
		mongoTemplate.remove(query(where("_id").is(dataId)), collection.getName());
	}

	@Override
	public Page<Object> list(TableDefine collection, Pageable pageable) {
		Query query = new Query();
		long count = mongoTemplate.count(query, collection.getName());
		query.skip(pageable.getOffset()).limit(pageable.getPageSize());
		@SuppressWarnings("rawtypes")
		List<Map> list = mongoTemplate.find(query, Map.class, collection.getName());
		return new PageImpl<Object>(
				list.stream().map(i -> i).collect(Collectors.toList()), pageable, count);
	}

	/**
	 * 根据表定义和数据获取数据ID,<br>
	 * 将所有的ID字段的字符串值相加，获得对象ID
	 *
	 * 
	 * @param table
	 * @param data
	 * @return
	 */
	private String getId(TableDefine table, Map<String, Object> data) {
		return StreamUtils.reduce(table.getColumns().stream().filter(ColumnDefine::isIdColumn), new StringBuilder(),
				(builder, current) -> builder.append(data.get(current.getName()))).toString();
	}

}

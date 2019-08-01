package com.xyyh.edc.data.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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

import com.google.common.collect.Lists;
import com.xyyh.edc.data.service.EdcDataService;
import com.xyyh.edc.meta.entity.Column;
import com.xyyh.edc.meta.entity.Table;
import com.xyyh.edc.meta.service.TableService;
import com.xyyh.util.StreamUtils;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;;

@Service
public class MongoEdcDataServiceImpl implements EdcDataService {

	@Autowired
	private TableService tableService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	@Transactional
	public Map<String, Object> save(String collection, Map<String, Object> data) {
		Optional<Table> table = tableService.findByName(collection);
		if (table.isPresent()) {
			Table t = table.get();
			// 根据表元数据判断数据ID
			// 如果没有指定ID列，使用默认的ID列
			String dataId = getId(t, data);
			if (StringUtils.isNotBlank(dataId)) {
				data.put("_id", dataId);
			}
			return mongoTemplate.save(data, table.get().getName());
		} else {
			return null;
		}
	}

	@Override
	public Object update(String collection, String dataId, Map<String, Object> data) {
		Optional<Table> table = tableService.findByName(collection);
		if (table.isPresent()) {
			Object exist = findOne(collection, dataId);
			if (Objects.isNull(exist)) {
				// TODO 如果不存在
				return null;
			} else {
				if (ObjectId.isValid(dataId)) {
					data.put("_id", new ObjectId(dataId));
				} else {
					data.put("_id", data);
				}
				return mongoTemplate.save(data, table.get().getName());
			}
		} else {
			return null;
		}
	}

	@Override
	public Object findOne(final String collection, final String dataId) {
		Optional<Table> table = tableService.findByName(collection);
		if (table.isPresent()) {
			// 如果是_id是ObjectId的字符串的话，一定会查找为ObjectId
			return mongoTemplate.findOne(query(where("_id").is(dataId)), Map.class, collection);
		} else {
			return null;
		}
	}

	@Override
	public void deleteById(String collection, String dataId) {
		mongoTemplate.remove(query(where("_id").is(dataId)), collection);
	}

	@Override
	public Page<Object> list(String collection, Pageable pageable) {
		Query query = new Query();
		long count = mongoTemplate.count(query, collection);
		query.skip(pageable.getOffset()).limit(pageable.getPageSize());
		@SuppressWarnings("rawtypes")
		List<Map> list = mongoTemplate.find(query, Map.class, collection);
		return new PageImpl<Object>(Lists.transform(list, i -> (Object) i), pageable, count);
	}

	public List<Column> getIdColumns(Table table) {
		return table.getColumns().stream().filter(Column::isIdColumn).collect(Collectors.toList());
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
	private String getId(Table table, Map<String, Object> data) {
		return StreamUtils.reduce(table.getColumns().stream().filter(Column::isIdColumn), new StringBuilder(),
				(builder, current) -> builder.append(data.get(current.getName()))).toString();
	}

}

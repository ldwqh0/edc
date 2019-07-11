package com.xyyh.edc.data.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyyh.edc.data.service.EdcDataService;
import com.xyyh.edc.meta.entity.Column;
import com.xyyh.edc.meta.entity.Table;
import com.xyyh.edc.meta.service.TableService;
import com.xyyh.util.StreamUtils;

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
		// 唯一键的控制
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

	private String getId(Table table, Map<String, Object> data) {
		return StreamUtils.reduce(table.getColumns().stream().filter(Column::isIdColumn), new StringBuilder(),
				(builder, current) -> builder.append(current)).toString();
	}

}

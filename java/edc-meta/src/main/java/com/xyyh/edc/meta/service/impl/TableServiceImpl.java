package com.xyyh.edc.meta.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.xyyh.edc.meta.converter.TableConverter;
import com.xyyh.edc.meta.dto.TableDto;
import com.xyyh.edc.meta.entity.QTable;
import com.xyyh.edc.meta.entity.Table;
import com.xyyh.edc.meta.repository.TableRepository;
import com.xyyh.edc.meta.service.TableService;

@Service
public class TableServiceImpl implements TableService {

	@Autowired
	private TableRepository tableRepository;

	@Autowired
	private TableConverter tableConverter;

	private final QTable qTable = QTable.table;

	@Override
	public Optional<Table> findById(Long id) {
		return tableRepository.findById(id);
	}

	@Override
	@Transactional
	public Table update(Long id, TableDto table) {
		Table result = tableRepository.getOne(id);
		tableConverter.copyProperties(result, table);
		return tableRepository.save(result);
	}

	@Override
	public Page<Table> find(String keywords, Pageable pageable) {
		BooleanBuilder condition = new BooleanBuilder();
		if (StringUtils.isNotBlank(keywords)) {
			condition.and(qTable.name.containsIgnoreCase(keywords));
		}
		return tableRepository.findAll(condition, pageable);
	}

	@Override
	@Transactional
	public Table save(TableDto table) {
		Table result = new Table();
		tableConverter.copyProperties(result, table);
		return tableRepository.save(result);
	}

	@Override
	public Optional<Table> findByName(String name) {
		return tableRepository.findByName(name);
	}

}

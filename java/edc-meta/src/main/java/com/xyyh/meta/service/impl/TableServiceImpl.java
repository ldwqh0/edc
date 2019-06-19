package com.xyyh.meta.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyyh.meta.converter.TableConverter;
import com.xyyh.meta.dto.TableDto;
import com.xyyh.meta.entity.Table;
import com.xyyh.meta.repository.TableRepository;
import com.xyyh.meta.service.TableService;

@Service
public class TableServiceImpl implements TableService {

	@Autowired
	private TableRepository tableRepository;

	@Autowired
	private TableConverter tableConverter;

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

}

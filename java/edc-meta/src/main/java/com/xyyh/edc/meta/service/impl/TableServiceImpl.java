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
import com.xyyh.edc.meta.exception.DdlCancelException;
import com.xyyh.edc.meta.listener.DdlEventArgs;
import com.xyyh.edc.meta.listener.TableDdlEventListener;
import com.xyyh.edc.meta.repository.TableRepository;
import com.xyyh.edc.meta.service.TableService;

@Service
public class TableServiceImpl implements TableService {

	@Autowired
	private TableRepository tableRepository;

	@Autowired
	private TableConverter tableConverter;

	private final QTable qTable = QTable.table;

	private TableDdlEventListener tableDdlEventListenere = new EmptyTableDdlEventListener();

	@Autowired(required = false)
	public void setTableDdlEventListenere(TableDdlEventListener tableDdlEventListenere) {
		this.tableDdlEventListenere = tableDdlEventListenere;
	}

	@Override
	public Optional<Table> findById(Long id) {
		return tableRepository.findById(id);
	}

	@Override
	@Transactional
	public Table update(Long id, TableDto table) {
		Table result = tableRepository.getOne(id);
		DdlEventArgs args = new DdlEventArgs();
		args.setOldData(tableConverter.toDto(result));
		try {
			tableDdlEventListenere.beforeCreate(args);
			if (args.isCancel()) {
				throw new DdlCancelException("create canceled");
			} else {
				tableConverter.copyProperties(result, table);
				Table result_ = tableRepository.save(result);
				args.setNewData(tableConverter.toDto(result_));
				tableDdlEventListenere.created(args);
				return result_;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (args.isCancel()) {
				throw new DdlCancelException("create canceled");
			}
		}
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

	private static class EmptyTableDdlEventListener implements TableDdlEventListener {

		@Override
		public void beforeCreate(DdlEventArgs args) {

		}

		@Override
		public void created(DdlEventArgs args) {

		}

		@Override
		public void createFailed(DdlEventArgs args) {

		}

		@Override
		public void beforeUpdate(DdlEventArgs args) {

		}

		@Override
		public void updated(DdlEventArgs args) {

		}

		@Override
		public void updateFailed(DdlEventArgs args) {

		}

		@Override
		public void beforeDelete(DdlEventArgs args) {

		}

		@Override
		public void deleted(DdlEventArgs args) {

		}

		@Override
		public void deleteFailed(DdlEventArgs args) {

		}

	}

}

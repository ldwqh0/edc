package com.xyyh.edc.meta.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Objects;
import com.querydsl.core.BooleanBuilder;
import com.xyyh.edc.meta.api.ColumnDefine;
import com.xyyh.edc.meta.api.TableDefine;
import com.xyyh.edc.meta.converter.ColumnConverter;
import com.xyyh.edc.meta.converter.TableConverter;
import com.xyyh.edc.meta.dto.ColumnDto;
import com.xyyh.edc.meta.dto.TableDto;
import com.xyyh.edc.meta.entity.Column;
import com.xyyh.edc.meta.entity.QTable;
import com.xyyh.edc.meta.entity.Table;
import com.xyyh.edc.meta.exception.DdlCancelException;
import com.xyyh.edc.meta.listener.DdlEventArgs;
import com.xyyh.edc.meta.listener.TableDdlEventListener;
import com.xyyh.edc.meta.repository.ColumnRepository;
import com.xyyh.edc.meta.repository.TableRepository;
import com.xyyh.edc.meta.service.JpaTableService;
import com.xyyh.util.StreamUtils;

@Service
public class TableServiceImpl implements JpaTableService {

	@Autowired
	private TableRepository tableRepository;

	@Autowired
	private ColumnRepository columnRepository;

	@Autowired
	private TableConverter tableConverter;

	private final QTable qTable = QTable.table;

	private TableDdlEventListener tableDdlEventListenere = new EmptyTableDdlEventListener();

	@Autowired
	private ColumnConverter columnConverter;

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
			tableDdlEventListenere.beforeUpdate(args);
			if (args.isCancel()) {
				return null;
			} else {
				copyProperties(result, table);
				Table result_ = tableRepository.save(result);
				args.setNewData(tableConverter.toDto(result_));
				tableDdlEventListenere.updated(args);
				return result_;
			}
		} catch (Exception e) {
			args.setThorwable(e);
			tableDdlEventListenere.updateFailed(args);
			throw e;
		} finally {
			if (args.isCancel()) {
				throw new DdlCancelException("update canceled");
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
		DdlEventArgs args = new DdlEventArgs();
		args.setOldData(table);
		tableDdlEventListenere.beforeCreate(args);
		try {
			if (args.isCancel()) {
				return null;
			} else {
				Table result = new Table();
				copyProperties(result, table);
				result = tableRepository.save(result);
				args.setNewData(tableConverter.toDto(result));
				tableDdlEventListenere.updated(args);
				return result;
			}
		} catch (Exception e) {
			args.setThorwable(e);
			tableDdlEventListenere.createFailed(args);
			throw e;
		} finally {
			if (args.isCancel()) {
				throw new DdlCancelException("create canceled");
			}
		}
	}

	@Override
	public Optional<TableDefine> findByName(String name) {
		Optional<Table> table = tableRepository.findByName(name);
		if (table.isPresent()) {
			return Optional.of(tableConverter.toDto(table));
		} else {
			return Optional.ofNullable(null);
		}
	}

	@Override
	public void delete(Long id) {
		Table table = tableRepository.getOne(id);
		DdlEventArgs args = new DdlEventArgs();
		try {
			args.setOldData(tableConverter.toDto(table));
			tableDdlEventListenere.beforeDelete(args);
			if (args.isCancel()) {
				return;
			} else {
				tableRepository.deleteById(id);
				tableDdlEventListenere.deleted(args);
				args.setNewData(null);
				return;
			}
		} catch (Exception e) {
			args.setThorwable(e);
			tableDdlEventListenere.deleteFailed(args);
			throw e;
		} finally {
			if (args.isCancel()) {
				throw new DdlCancelException("create canceled");
			}
		}
	}

	private Table copyProperties(final Table table, TableDto dto) {
		tableConverter.copyProperties(table, dto);
		List<ColumnDefine> newColumns = dto.getColumns();
		List<Column> modelColumns = table.getColumns();
		// 删除列对象中，在原始对象中存在，但在新对象中不存在的列
		if (!table.isNew() && CollectionUtils.isNotEmpty(modelColumns)) {
			// 如果传入对象是空的，删除所有的原始列对象
			if (CollectionUtils.isEmpty(newColumns)) {
				columnRepository.deleteInBatch(modelColumns);
			} else {
				// 否则找出所有在原始列列表中存在，但在新列表中不存在的对象，并删除
				List<Column> columnToDelete = modelColumns.stream()
						.filter(item -> !newColumns.stream().anyMatch(c -> Objects.equal(c.getId(), item.getId())))
						.collect(Collectors.toList());
				columnRepository.deleteAll(columnToDelete);
//				columnRepository.deleteInBatch(columnToDelete);
			}
		}

		if (CollectionUtils.isNotEmpty(newColumns)) {
			List<Column> columns = StreamUtils.map(newColumns.stream(), (index, item) -> {
				// 判断column是否新的
				Column column = item.isNew() ? new Column() : columnRepository.getOne(item.getId());
				column.setOrder(index);
				return columnConverter.copyProperties(column, (ColumnDto) item);
			}).collect(Collectors.toList());
			table.setColumns(columns);
		}
		return table;
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

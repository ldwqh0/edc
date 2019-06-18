package com.xyyh.meta.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.dm.common.converter.AbstractConverter;
import com.xyyh.meta.dto.ColumnDto;
import com.xyyh.meta.dto.TableDto;
import com.xyyh.meta.entity.Column;
import com.xyyh.meta.entity.Table;

@Component
public class TableConverter extends AbstractConverter<Table, TableDto> {

	@Override
	protected TableDto toDtoActual(Table model) {
		TableDto dto = new TableDto();
		dto.setId(model.getId());
		dto.setName(model.getName());
		List<Column> columns = model.getColumns();
		if (CollectionUtils.isNotEmpty(columns)) {
			List<ColumnDto> columnDtos = columns.stream().map(this::toColumnDto).collect(Collectors.toList());
			dto.setColumns(columnDtos);
		}
		return dto;
	}

	@Override
	public Table copyProperties(Table model, TableDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	private ColumnDto toColumnDto(Column column) {
		ColumnDto dto = new ColumnDto();
		dto.setFieldName(column.getFieldName());
		dto.setLength(column.getLength());
		dto.setName(column.getName());
		dto.setNullable(column.getNullable());
		dto.setType(column.getType());
		return dto;
	}
}

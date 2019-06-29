package com.xyyh.meta.converter;

import org.springframework.stereotype.Component;

import com.dm.common.converter.AbstractConverter;
import com.xyyh.meta.dto.TableDto;
import com.xyyh.meta.entity.Table;

@Component
public class TableConverter extends AbstractConverter<Table, TableDto> {

	@Override
	protected TableDto toDtoActual(Table model) {
		TableDto dto = new TableDto();
		dto.setId(model.getId());
		dto.setName(model.getName());
		dto.setColumns(model.getColumns());
		return dto;
	}

	public TableDto toTableDto(Table model) {
		TableDto dto = new TableDto();
		dto.setId(model.getId());
		dto.setName(model.getName());
		return dto;
	}

	@Override
	public Table copyProperties(Table model, TableDto dto) {
		model.setName(dto.getName());
		model.setColumns(dto.getColumns());
		return model;
	}

}

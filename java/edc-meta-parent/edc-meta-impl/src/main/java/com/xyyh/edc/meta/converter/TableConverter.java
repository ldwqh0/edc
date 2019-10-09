package com.xyyh.edc.meta.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xyyh.edc.common.converter.AbstractConverter;
import com.xyyh.edc.meta.dto.TableDto;
import com.xyyh.edc.meta.entity.Table;

@Component
public class TableConverter extends AbstractConverter<Table, TableDto> {

	@Autowired
	private ColumnConverter columnConverter;

	@Override
	protected TableDto toDtoActual(Table model) {
		TableDto dto = new TableDto();
		dto.setId(model.getId());
		dto.setName(model.getName());
		dto.setTitle(model.getTitle());
		dto.setColumns(columnConverter.toDto(model.getColumns()));
		dto.setFormId(model.getFormId());
		return dto;
	}

	public TableDto toTableDto(Table model) {
		TableDto dto = new TableDto();
		dto.setId(model.getId());
		dto.setName(model.getName());
		dto.setTitle(model.getTitle());
		return dto;
	}

	@Override
	public Table copyProperties(Table model, TableDto dto) {
		model.setName(dto.getName());
		model.setTitle(dto.getTitle());
		return model;
	}

}

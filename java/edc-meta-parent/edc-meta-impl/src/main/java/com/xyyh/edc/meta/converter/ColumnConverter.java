package com.xyyh.edc.meta.converter;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.xyyh.edc.common.converter.AbstractConverter;
import com.xyyh.edc.meta.dto.ColumnDto;
import com.xyyh.edc.meta.dto.ColumnFormAttributesDto;
import com.xyyh.edc.meta.entity.Column;
import com.xyyh.edc.meta.entity.ColumnFormAttributes;

@Component
public class ColumnConverter extends AbstractConverter<Column, ColumnDto> {

	@Override
	protected ColumnDto toDtoActual(Column model) {
		ColumnDto dto = new ColumnDto();
		dto.setId(model.getId());
		dto.setIdColumn(model.isIdColumn());
		dto.setLength(model.getLength());
		dto.setName(model.getName());
		dto.setNullable(model.getNullable());
		dto.setType(model.getType());
		return dto;
	}

	@Override
	public Column copyProperties(final Column model, ColumnDto dto) {
		model.setIdColumn(dto.isIdColumn());
		model.setLength(dto.getLength());
		model.setName(dto.getName());
		model.setNullable(dto.isNullable());
		model.setType(dto.getType());
		return model;
	}

}
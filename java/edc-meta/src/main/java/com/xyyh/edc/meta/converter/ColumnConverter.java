package com.xyyh.edc.meta.converter;

import org.springframework.stereotype.Component;

import com.dm.common.converter.AbstractConverter;
import com.xyyh.edc.meta.dto.ColumnDto;
import com.xyyh.edc.meta.entity.Column;

@Component
public class ColumnConverter extends AbstractConverter<Column, ColumnDto> {

	@Override
	protected ColumnDto toDtoActual(Column model) {
		ColumnDto dto = new ColumnDto();
		dto.setFieldName(model.getFieldName());
		dto.setId(model.getId());
		dto.setIdColumn(model.isIdColumn());
		dto.setLength(model.getLength());
		dto.setMax(model.getMax());
		dto.setMin(model.getMin());
		dto.setName(model.getName());
		dto.setNullable(model.getNullable());
		dto.setOrder(model.getOrder());
		dto.setType(model.getType());
		return dto;
	}

	@Override
	public Column copyProperties(final Column model, ColumnDto dto) {
		model.setFieldName(dto.getFieldName());
		model.setIdColumn(dto.isIdColumn());
		model.setLength(dto.getLength());
		model.setMax(dto.getMax());
		model.setMin(dto.getMin());
		model.setName(dto.getName());
		model.setNullable(dto.getNullable());
		// 在数据写入时不要设置顺序
//		model.setOrder(dto.getOrder());
		model.setType(dto.getType());
		return model;
	}

}

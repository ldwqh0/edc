package com.xyyh.edc.meta.converter;

import org.springframework.stereotype.Component;

import com.dm.common.converter.AbstractConverter;
import com.xyyh.edc.meta.dto.ColumnDto;
import com.xyyh.edc.meta.entity.Column;

@Component
public class ColumnConverter extends AbstractConverter<Column, ColumnDto> {

	@Override
	protected ColumnDto toDtoActual(Column model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Column copyProperties(Column model, ColumnDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}

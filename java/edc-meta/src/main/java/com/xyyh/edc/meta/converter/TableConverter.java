package com.xyyh.edc.meta.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dm.common.converter.AbstractConverter;
import com.xyyh.edc.meta.dto.TableDto;
import com.xyyh.edc.meta.entity.Table;
import com.xyyh.util.StreamUtils;

@Component
public class TableConverter extends AbstractConverter<Table, TableDto> {

	@Autowired
	private ColumnConverter columnConverter;

	@Override
	protected TableDto toDtoActual(Table model) {
		TableDto dto = new TableDto();
		dto.setId(model.getId());
		dto.setName(model.getName());
		dto.setColumns(columnConverter.toDto(model.getColumns()));
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
		org.springframework.data.util.StreamUtils.createStreamFromIterator(model.getColumns().iterator());
		StreamUtils.forEach(dto.getColumns().stream(), (index, item) -> {
			item.setOrder(index);
		});
//		model.setColumns(dto.getColumns());
		return model;
	}

}

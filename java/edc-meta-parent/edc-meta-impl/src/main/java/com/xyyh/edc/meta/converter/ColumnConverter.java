package com.xyyh.edc.meta.converter;

import java.util.Objects;

import org.springframework.stereotype.Component;

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
		dto.setFormAttributes(toColumnAttributesDto(model.getFormAttributes()));
		
		return dto;
	}

	@Override
	public Column copyProperties(final Column model, ColumnDto dto) {
		model.setIdColumn(dto.isIdColumn());
		model.setLength(dto.getLength());
		model.setName(dto.getName());
		model.setNullable(dto.isNullable());
		com.xyyh.edc.meta.api.ColumnFormAttributes attributesSource = dto.getFormAttributes();
		if (Objects.isNull(attributesSource)) {
			model.setFormAttributes(null);
		} else {
			ColumnFormAttributes attributes = model.getFormAttributes();
			if (Objects.isNull(attributes)) {
				attributes = new ColumnFormAttributes();
			}
			attributes.setColumn(model);
			attributes.setMax(attributesSource.getMax());
			attributes.setMin(attributesSource.getMin());
			attributes.setTitle(attributesSource.getTitle());
			attributes.setInputControl(attributesSource.getInputControl());
			attributes.setTrueLabel(attributesSource.getTrueLabel());
			attributes.setFalseLabel(attributesSource.getFalseLabel());
			attributes.setOptions(attributesSource.getOptions());
			model.setFormAttributes(attributes);
		}
		model.setType(dto.getType());
		return model;
	}

	private ColumnFormAttributesDto toColumnAttributesDto(ColumnFormAttributes source) {
		if (Objects.isNull(source)) {
			return null;
		} else {
			ColumnFormAttributesDto dto = new ColumnFormAttributesDto();
			dto.setMax(source.getMax());
			dto.setTitle(source.getTitle());
			dto.setMin(source.getMin());
			dto.setInputControl(source.getInputControl());
			dto.setTrueLabel(source.getTrueLabel());
			dto.setFalseLabel(source.getFalseLabel());
			dto.setOptions(source.getOptions());
			return dto;
		}
	}

}
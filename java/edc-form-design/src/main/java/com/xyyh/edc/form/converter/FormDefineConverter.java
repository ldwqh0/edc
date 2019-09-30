package com.xyyh.edc.form.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.xyyh.edc.common.converter.AbstractConverter;
import com.xyyh.edc.form.dto.FormDefineDto;
import com.xyyh.edc.form.dto.WidgetDto;
import com.xyyh.edc.form.entity.FormDefine;
import com.xyyh.edc.form.entity.Widget;

@Component
public class FormDefineConverter extends AbstractConverter<FormDefine, FormDefineDto> {

	@Override
	protected FormDefineDto toDtoActual(FormDefine model) {
		FormDefineDto dto = toTableDto(model);
		dto.setMemo(model.getMemo());
		dto.setOptions(model.getOptions());
		dto.setWidgets(toWidgetDto(model.getWidgets()));
		return dto;
	}

	@Override
	public FormDefine copyProperties(FormDefine model, FormDefineDto dto) {
		model.setAction(dto.getAction());
		model.setMemo(dto.getMemo());
		model.setName(dto.getName());
		model.setOptions(dto.getOptions());
		model.setWidgets(toWidgets(dto.getWidgets()));
		return model;
	}

	public FormDefineDto toTableDto(FormDefine model) {
		FormDefineDto dto = new FormDefineDto();
		dto.setAction(model.getAction());
		dto.setId(model.getId());
		dto.setName(model.getName());
		return dto;
	}

	private Widget toWidget(WidgetDto widgetDto) {
		Widget model = new Widget();
		model.setIcon(widgetDto.getIcon());
		model.setKey(widgetDto.getKey());
		model.setName(widgetDto.getName());
		model.setOptions(widgetDto.getOptions());
		model.setRules(widgetDto.getRules());
		model.setType(widgetDto.getType());
		model.setDataSource(widgetDto.getDataSource());
		model.setColumns(widgetDto.getColumns());
		return model;
	}

	private List<Widget> toWidgets(List<WidgetDto> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return Collections.emptyList();
		} else {
			return dtos.stream().map(this::toWidget).collect(Collectors.toList());
		}
	}

	private WidgetDto toWidgetDto(Widget widget) {
		WidgetDto dto = new WidgetDto();
		dto.setIcon(widget.getIcon());
		dto.setKey(widget.getKey());
		dto.setName(widget.getName());
		dto.setOptions(widget.getOptions());
		dto.setRules(widget.getRules());
		dto.setType(widget.getType());
		dto.setDataSource(widget.getDataSource());
		dto.setColumns(widget.getColumns());
		return dto;
	}

	private List<WidgetDto> toWidgetDto(List<Widget> widgets) {
		if (CollectionUtils.isEmpty(widgets)) {
			return Collections.emptyList();
		} else {
			return widgets.stream().map(this::toWidgetDto).collect(Collectors.toList());
		}
	}

}

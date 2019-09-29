package com.xyyh.edc.form.dto;

import lombok.Data;

@Data
public class WidgetDto {
	/**
	 * 组件名称
	 */
	private String name;
	private String key;
	private String icon;
	private String type;
	private String options;
	private String rules;
	private String dataSource;
}

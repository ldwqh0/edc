package com.xyyh.edc.form.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class FormDefineDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String options;

	private List<WidgetDto> widgets;

	/**
	 * 表单提交地址
	 */
	private String action;

	/**
	 * 表单说明
	 */
	private String memo;
}

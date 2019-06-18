package com.xyyh.meta.dto;

import java.io.Serializable;

import com.xyyh.meta.entity.Column.Types;

import lombok.Data;

@Data
public class ColumnDto implements Serializable {

	private static final long serialVersionUID = 8465444608184890330L;
	/**
	 * 列名称
	 */
	private String name;
	/**
	 * 列字段名称
	 */
	private String fieldName;

	/**
	 * 字段长度
	 */
	private Integer length;

	/**
	 * 字段是为空
	 */
	private Boolean nullable = Boolean.TRUE;

	/**
	 * 字段类型
	 */
	private Types type;
}

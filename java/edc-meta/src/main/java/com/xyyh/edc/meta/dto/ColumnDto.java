package com.xyyh.edc.meta.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyyh.edc.meta.entity.Column.Types;

import lombok.Data;

@Data
public class ColumnDto implements Serializable {

	private static final long serialVersionUID = 5373677095536709827L;

	private Long id;

	private String name;

	/**
	 * 列字段名称
	 */
	private String fieldName;

	private boolean idColumn;

	/**
	 * 指定字段的取值范围， 对于数值型，进行值校验，对于字符串，进行长度校验
	 */
	private Integer min;

	/**
	 * 指定字段的取值范围
	 */
	private Integer max;

	/**
	 * 字段长度
	 */
	private Integer length;

	/**
	 * 字段是为空
	 */
	private Boolean nullable = Boolean.TRUE;

	private Integer order;

	/**
	 * 字段类型
	 */
	private Types type;

	@JsonIgnore
	public boolean isNew() {
		return Objects.isNull(id);
	}
}

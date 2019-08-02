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

	private boolean idColumn;

	/**
	 * 字段长度
	 */
	private Integer length;

	/**
	 * 字段是为空
	 */
	private Boolean nullable = Boolean.TRUE;

	/**
	 * 字段排序
	 */
	private Integer order;

	/**
	 * 字段类型
	 */
	private Types type;

	private ColumnFormAttributesDto formAttributes;

	@JsonIgnore
	public boolean isNew() {
		return Objects.isNull(id);
	}
}

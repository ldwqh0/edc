package com.xyyh.edc.meta.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.xyyh.edc.meta.api.ColumnDefine;
import com.xyyh.edc.meta.api.ColumnFormAttributes;
import com.xyyh.edc.meta.api.ColumnTypes;

@JsonInclude(Include.NON_NULL)
public class ColumnDto implements Serializable, ColumnDefine {

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
	private ColumnTypes type;

	private ColumnFormAttributesDto formAttributes;

	@Override
	public boolean isNullable() {
		return nullable;
	}

	@Override
	public ColumnFormAttributes getFormAttributes() {
		return formAttributes;
	}

	@Override
	public boolean isNew() {
		return Objects.isNull(id);
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isIdColumn() {
		return idColumn;
	}

	@Override
	public Integer getLength() {
		return length;
	}

	@Override
	public Integer getOrder() {
		return order;
	}

	@Override
	public ColumnTypes getType() {
		return type;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIdColumn(boolean idColumn) {
		this.idColumn = idColumn;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public void setType(ColumnTypes type) {
		this.type = type;
	}

	public void setFormAttributes(ColumnFormAttributesDto formAttributes) {
		this.formAttributes = formAttributes;
	}

}

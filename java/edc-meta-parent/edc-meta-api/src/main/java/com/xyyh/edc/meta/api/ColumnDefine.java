package com.xyyh.edc.meta.api;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface ColumnDefine extends Serializable {

	/**
	 * 字段唯一识别符
	 * 
	 * @return
	 */
	public Long getId();

	/**
	 * 字段名称
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 是否唯一识别符字段
	 * 
	 * @return
	 */
	public boolean isIdColumn();

	/**
	 * 字段长度
	 */
	public Integer getLength();

	/**
	 * 字段是为空
	 */
	public boolean isNullable();

	/**
	 * 字段排序
	 */
	public Integer getOrder();

	/**
	 * 字段类型
	 */
	public ColumnTypes getType();

	/**
	 * 字段的表单属性
	 * 
	 * @return
	 */
	public ColumnFormAttributes getFormAttributes();

	/**
	 * 是否新列
	 * 
	 * @return
	 */
	@JsonIgnore
	public boolean isNew();
}

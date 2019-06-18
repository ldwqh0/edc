package com.xyyh.meta.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Column implements Serializable {

	private static final long serialVersionUID = -69338300850276439L;

	public enum Types {
		NUMBER,
		STRING
	}

	/**
	 * 列名称
	 */
	@javax.persistence.Column(name = "name_", length = 200)
	private String name;
	/**
	 * 列字段名称
	 */
	@javax.persistence.Column(name = "field_name_", length = 200)
	private String fieldName;

	/**
	 * 字段长度
	 */
	@javax.persistence.Column(name = "length_")
	private Integer length;

	/**
	 * 字段是为空
	 */
	private Boolean nullable = Boolean.TRUE;

	/**
	 * 字段类型
	 */
	@Enumerated(EnumType.STRING)
	@javax.persistence.Column(name = "type_", length = 50)
	private Types type;
}

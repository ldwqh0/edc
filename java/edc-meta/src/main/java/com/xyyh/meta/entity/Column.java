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
		DECIMAL,
		STRING,
		DATETIME
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
	 * 指定字段的取值范围， 对于数值型，进行值校验，对于字符串，进行长度校验
	 */
	@javax.persistence.Column(name = "min_")
	private Integer min;

	/**
	 * 指定字段的取值范围
	 */
	@javax.persistence.Column(name = "max_")
	private Integer max;

	/**
	 * 字段长度
	 */
	@javax.persistence.Column(name = "length_")
	private Integer length;

	/**
	 * 字段是为空
	 */
	@javax.persistence.Column(name = "nullable_", nullable = true)
	private Boolean nullable = Boolean.TRUE;

	/**
	 * 字段类型
	 */
	@Enumerated(EnumType.STRING)
	@javax.persistence.Column(name = "type_", length = 50)
	private Types type;
}

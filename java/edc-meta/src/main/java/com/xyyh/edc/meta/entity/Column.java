package com.xyyh.edc.meta.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.dm.common.entity.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Column extends AbstractEntity {

	private static final long serialVersionUID = -69338300850276439L;

	public enum Types {
		/**
		 * 整数类型
		 */
		INTEGER,
		/**
		 * 实数类型
		 */
		DECIMAL,
		/**
		 * 字符串类型
		 */
		STRING,
		/**
		 * 日期
		 */
		DATE,
		/**
		 * 日期时间
		 */
		DATETIME,
		/**
		 * boolean类型
		 */
		BOOLEAN,
		/**
		 * 二进制
		 */
		BINARY
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

	@javax.persistence.Column(name = "is_id_cloumn_")
	private boolean idColumn;

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

	@javax.persistence.Column(name = "sort_")
	private Integer order;

	/**
	 * 字段类型
	 */
	@Enumerated(EnumType.STRING)
	@javax.persistence.Column(name = "type_", length = 50)
	private Types type;

}

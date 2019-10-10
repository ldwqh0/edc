package com.xyyh.edc.enums;

import com.xyyh.edc.meta.api.ColumnTypes;

public enum MySqlColumnTypes {
	/**
	 * 长整数类型
	 */
	LONG("bigint"),
	/**
	 * 整数类型
	 */
	INTEGER("int"),
	/**
	 * 实数类型
	 */
	DECIMAL("decimal"),
	/**
	 * 字符串类型
	 */
	STRING("varchar"),
	/**
	 * 日期
	 */
	DATE("date"),
	/**
	 * 日期时间
	 */
	DATETIME("datetime"),
	/**
	 * boolean类型
	 */
	BOOLEAN("int"),
	/**
	 * 二进制
	 */
	BINARY("binary");
	
	private String value;
	
	MySqlColumnTypes(String value){
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public static MySqlColumnTypes getEnum(String value) {
		for (MySqlColumnTypes v : values())
			if (v.getValue().equals(value))
				return v;
		throw new IllegalArgumentException();
	}
	
	public static MySqlColumnTypes getEnum(ColumnTypes types) {
		for (MySqlColumnTypes v : values()) {
			if(v.toString().equals(types.toString())) {
				return v;
			}
		}
		throw new IllegalArgumentException();
	}
	
}

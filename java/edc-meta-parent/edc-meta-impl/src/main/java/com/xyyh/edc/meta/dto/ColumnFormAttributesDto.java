package com.xyyh.edc.meta.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.xyyh.edc.meta.api.ColumnFormAttributes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColumnFormAttributesDto implements Serializable, ColumnFormAttributes {
	private static final long serialVersionUID = 646150343127467359L;

	/**
	 * 字段显示的标题
	 */
	private String title;

	/**
	 * 允许输入的最大值
	 */
	private BigDecimal min;

	/**
	 * 允许输入的最小值
	 */
	private BigDecimal max;
}

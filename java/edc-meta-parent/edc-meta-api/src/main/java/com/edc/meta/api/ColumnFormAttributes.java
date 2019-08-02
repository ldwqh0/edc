package com.edc.meta.api;

import java.io.Serializable;
import java.math.BigDecimal;

public interface ColumnFormAttributes extends Serializable {

	/**
	 * 字段显示的标题
	 */
	public String getTitle();

	/**
	 * 允许输入的最大值
	 */
	public BigDecimal getMin();

	/**
	 * 允许输入的最小值
	 */
	public BigDecimal getMax();
}

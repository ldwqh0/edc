package com.xyyh.edc.meta.api;

import java.io.Serializable;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
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

	/**
	 * 输入控件类型
	 * 
	 * @return
	 */
	public InputControls getInputControl();

	/**
	 * 真值标签
	 * 
	 * @return
	 */
	public String getTrueLabel();

	/**
	 * 假值标签
	 * 
	 * @return
	 */
	public String getFalseLabel();

	/**
	 * 在字符控件的情况下，如果使用下拉选或者单选时，选项的列表
	 * 
	 * @return
	 */
	public String getOptions();
}

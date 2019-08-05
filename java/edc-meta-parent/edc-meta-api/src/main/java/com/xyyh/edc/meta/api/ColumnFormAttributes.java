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

	public ValueSourceTypes getValueSourceType();

	/**
	 * 该选项字段的固定值域
	 * 
	 * @return
	 */
	public String getFixedValue();
	
	public FixedValueTypes getFixedValueType();

	/**
	 * 列表单的额外条件，使用json保存的字符串
	 * 
	 * @return
	 */
	public String getOptions();
}

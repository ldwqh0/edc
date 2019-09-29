package com.xyyh.edc.form.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OrderColumn;

import com.xyyh.edc.common.entity.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 表单实体定义
 * 
 * @author ldwqh0@outlook.com
 *
 */
@Entity(name = "edc_form_")
@Getter
@Setter
public class FormDefine extends AbstractEntity {

	private static final long serialVersionUID = -4246866678492238520L;

	/**
	 * 表单名称
	 */
	@Column(name = "name_", length = 200)
	private String name;

	/**
	 * 表单配置项
	 */
	@Lob
	@Column(name = "options_")
	private String options;

	/**
	 * 窗口组件列表
	 */
	@ElementCollection(fetch = FetchType.LAZY)
	@OrderColumn(name = "order_")
	@CollectionTable(name = "edc_form_widget_")
	private List<Widget> widgets;

	/**
	 * 表单提交地址
	 */
	@Column(name = "action_", length = 200)
	private String action;

	/**
	 * 表单说明
	 */
	@Column(name = "memo_", length = 200)
	private String memo;

}

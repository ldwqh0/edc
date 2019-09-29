package com.xyyh.edc.form.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

import lombok.Data;

@Embeddable
@Data
public class Widget {
	/**
	 * 组件名称
	 */
	@Column(name = "name_", length = 50)
	private String name;

	@Column(name = "key_", length = 100)
	private String key;

	@Column(name = "icon_", length = 100)
	private String icon;

	@Column(name = "type_", length = 50)
	private String type;

//	private Long order;

	@Lob
	@Column(name = "options_")
	private String options;

	@Column(name = "rules_")
	private String rules;

	@Column(name = "data_source_")
	private String dataSource;
}

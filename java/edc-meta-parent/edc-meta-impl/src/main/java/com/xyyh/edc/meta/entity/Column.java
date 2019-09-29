package com.xyyh.edc.meta.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.xyyh.edc.common.entity.AbstractEntity;
import com.xyyh.edc.meta.api.ColumnTypes;

import lombok.Getter;
import lombok.Setter;

/**
 * 字段列的存储属性
 * 
 * @author LiDong
 *
 */
@Getter
@Setter
@Entity(name = "edc_table_column_")
public class Column extends AbstractEntity {

	private static final long serialVersionUID = -69338300850276439L;

	/**
	 * 列名称
	 */
	@javax.persistence.Column(name = "name_", length = 200)
	private String name;

	@javax.persistence.Column(name = "is_id_cloumn_")
	private boolean idColumn;

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

	@javax.persistence.Column(name = "order_")
	private Integer order;

	/**
	 * 字段类型
	 */
	@Enumerated(EnumType.STRING)
	@javax.persistence.Column(name = "type_", length = 50)
	private ColumnTypes type;

//	@OneToOne(mappedBy = "column", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@PrimaryKeyJoinColumn
//	private ColumnFormAttributes formAttributes;

}

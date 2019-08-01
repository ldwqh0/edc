package com.xyyh.edc.meta.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.dm.common.entity.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "edc_table_")
@Getter
@Setter
public class Table extends AbstractEntity {
	private static final long serialVersionUID = 6295989944983358320L;

	@javax.persistence.Column(name = "name_", unique = true, nullable = false)
	private String name;

	@javax.persistence.Column(name = "title_", nullable = false)
	private String title;

	@OneToMany(cascade = CascadeType.ALL)
	@OrderBy("order ASC")
	@JoinColumn(name = "table_id_")
	private List<Column> columns;

}

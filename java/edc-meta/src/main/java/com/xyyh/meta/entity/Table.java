package com.xyyh.meta.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import com.dm.common.entity.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "edc_table_")
@Getter
@Setter
public class Table extends AbstractEntity {
	private static final long serialVersionUID = 6295989944983358320L;

	@javax.persistence.Column(name = "name_", unique = true)
	private String name;

	@ElementCollection
	@CollectionTable(name = "edc_table_column_", joinColumns = {
			@JoinColumn(name = "table_id_", referencedColumnName = "id_")
	})
	private List<Column> columns;
}

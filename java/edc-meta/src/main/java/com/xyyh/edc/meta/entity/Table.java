package com.xyyh.edc.meta.entity;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import org.apache.commons.collections4.CollectionUtils;

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

	@Transient
	private List<Column> getIdColumn() {
		if (CollectionUtils.isNotEmpty(columns)) {
			columns.stream().filter(c -> c.isIdColumn()).collect(Collectors.toList());
		} else {

		}

		return null;
	}
}

package com.xyyh.edc.meta.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.xyyh.edc.meta.api.ColumnDefine;
import com.xyyh.edc.meta.api.TableDefine;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class TableDto implements Serializable, TableDefine {
	private static final long serialVersionUID = -4660389644726562389L;
	private Long id;
	private String name;
	private String title;
	private List<ColumnDto> columns;

	public List<ColumnDefine> getColumns() {
		return columns.stream().map(a -> a).collect(Collectors.toList());
	}
}

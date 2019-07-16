package com.xyyh.edc.meta.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class TableDto implements Serializable {
	private static final long serialVersionUID = -4660389644726562389L;
	private Long id;
	private String name;
	private List<ColumnDto> columns;
}

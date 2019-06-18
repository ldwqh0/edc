package com.xyyh.meta.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class TableDto implements Serializable {
	private static final long serialVersionUID = -4660389644726562389L;
	private Long id;
	private String name;
	private List<ColumnDto> columns;

}

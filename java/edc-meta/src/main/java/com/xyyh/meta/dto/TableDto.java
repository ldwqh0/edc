package com.xyyh.meta.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.xyyh.meta.entity.Column;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class TableDto implements Serializable {
	private static final long serialVersionUID = -4660389644726562389L;
	private Long id;
	private String name;
	private List<Column> columns;
}

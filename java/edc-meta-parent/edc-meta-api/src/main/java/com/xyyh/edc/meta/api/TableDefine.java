package com.xyyh.edc.meta.api;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public interface TableDefine extends Serializable {
	public Long getId();

	public String getName();

	public String getTitle();

	public List<ColumnDefine> getColumns();
}

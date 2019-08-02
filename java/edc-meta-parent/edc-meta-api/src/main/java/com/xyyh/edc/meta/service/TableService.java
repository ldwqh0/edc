package com.xyyh.edc.meta.service;

import java.util.Optional;

import com.xyyh.edc.meta.api.TableDefine;

public interface TableService {

	/**
	 * 根据表名称查找表定义
	 * 
	 * @param collection
	 * @return
	 */
	public Optional<TableDefine> findByName(String collection);

}

package com.xyyh.edc.data.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xyyh.edc.meta.api.TableDefine;

/**
 * 数据存储引擎服务 <br>
 * 
 * @author LiDong
 *
 */
public interface EdcDataService {

	/**
	 * 使用唯一的识别符将数据保存到持久化服务，数据中使用持久化必须提供唯一的数据识别符
	 * 
	 * @param table  定义的数据结构
	 * @param dataId 唯一数据识别符
	 * @param data   数据
	 * @return
	 */
	public Map<String, Object> save(TableDefine table, String dataId, Map<String, Object> data);

	public Map<String, Object> findOne(TableDefine table, String dataId);

	public void deleteById(TableDefine table, String dataId);

	public Page<Map<String, Object>> list(TableDefine table, Pageable pageable);

	public Map<String, Object> update(TableDefine table, String dataId, Map<String, Object> data);
}

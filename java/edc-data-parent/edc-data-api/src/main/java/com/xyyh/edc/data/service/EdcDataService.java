package com.xyyh.edc.data.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xyyh.edc.meta.api.TableDefine;

public interface EdcDataService {

	public Map<String, Object> save(TableDefine table, Map<String, Object> data);

	public Object findOne(TableDefine table, String dataId);

	public void deleteById(TableDefine table, String dataId);

	public Page<Object> list(TableDefine table, Pageable pageable);

	public Object update(TableDefine table, String dataId, Map<String, Object> data);
}

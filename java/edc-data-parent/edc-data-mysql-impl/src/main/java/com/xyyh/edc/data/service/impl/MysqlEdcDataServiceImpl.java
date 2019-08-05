package com.xyyh.edc.data.service.impl;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xyyh.edc.data.service.EdcDataService;
import com.xyyh.edc.meta.api.TableDefine;

//@Service
public class MysqlEdcDataServiceImpl implements EdcDataService {

	@Override
	public Map<String, Object> save(TableDefine table,String dataId, Map<String, Object> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findOne(TableDefine table, String dataId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(TableDefine table, String dataId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Map<String, Object>> list(TableDefine table, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> update(TableDefine table, String dataId, Map<String, Object> data) {
		// TODO Auto-generated method stub
		return null;
	}


}

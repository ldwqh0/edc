package com.xyyh.edc.data.service.impl;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xyyh.edc.data.service.EdcDataService;

@Service
public class MysqlEdcDataServiceImpl implements EdcDataService {

	@Override
	public Map<String, Object> save(String collection, Map<String, Object> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findOne(String collection, Object dataId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(String collection, Object dataId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<Object> list(String collection, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}

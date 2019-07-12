package com.xyyh.edc.data.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EdcDataService {

	public Map<String, Object> save(String collection, Map<String, Object> data);

	public Object findOne(String collection, Object dataId);

	public void deleteById(String collection, Object dataId);

	public Page<Object> list(String collection, Pageable pageable);

}
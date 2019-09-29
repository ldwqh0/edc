package com.xyyh.edc.form.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.xyyh.edc.form.converter.FormDefineConverter;
import com.xyyh.edc.form.dto.FormDefineDto;
import com.xyyh.edc.form.entity.FormDefine;
import com.xyyh.edc.form.entity.QFormDefine;
import com.xyyh.edc.form.repository.FormDefineRepository;
import com.xyyh.edc.form.service.FormDefineService;

@Service
public class FormDefineServiceImpl implements FormDefineService {

	@Autowired
	private FormDefineRepository formDefineRepository;

	@Autowired
	private FormDefineConverter formDefineConverter;

	private final QFormDefine qFormDefine = QFormDefine.formDefine;

	@Override
	@Transactional
	public FormDefine save(FormDefineDto form) {
		FormDefine entity = new FormDefine();
		formDefineConverter.copyProperties(entity, form);
		return formDefineRepository.save(entity);
	}

	@Override
	@Transactional
	public FormDefine update(Long id, FormDefineDto form) {
		FormDefine entity = formDefineRepository.getOne(id);
		formDefineConverter.copyProperties(entity, form);
		return formDefineRepository.save(entity);
	}

	@Override
	public Optional<FormDefine> findById(Long id) {
		return formDefineRepository.findById(id);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<FormDefine> search(String key, Pageable pageable) {
		BooleanBuilder query = new BooleanBuilder();
		if (StringUtils.isNotBlank(key)) {
			query.or(qFormDefine.action.containsIgnoreCase(key));
			query.or(qFormDefine.memo.containsIgnoreCase(key));
			query.or(qFormDefine.name.containsIgnoreCase(key));
		}
		return formDefineRepository.findAll(query, pageable);
	}

}

package com.xyyh.edc.form.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xyyh.edc.form.dto.FormDefineDto;
import com.xyyh.edc.form.entity.FormDefine;

public interface FormDefineService {

	public FormDefine save(FormDefineDto form);

	public FormDefine update(Long id, FormDefineDto form);

	public Optional<FormDefine> findById(Long id);

	public void deleteById(Long id);

	public Page<FormDefine> search(String keywords, Pageable pageable);

}

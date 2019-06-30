package com.xyyh.edc.meta.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xyyh.edc.meta.dto.TableDto;
import com.xyyh.edc.meta.entity.Table;

public interface TableService {

	public Optional<Table> findById(Long id);

	public Table update(Long id, TableDto table);

	public Page<Table> find(String keywords, Pageable pageable);

	public Table save(TableDto table);

}

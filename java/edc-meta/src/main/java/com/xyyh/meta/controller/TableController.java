package com.xyyh.meta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyyh.meta.converter.TableConverter;
import com.xyyh.meta.dto.TableDto;
import com.xyyh.meta.service.TableService;

@RestController
@RequestMapping("tables")
public class TableController {

	@Autowired
	private TableService tableService;

	@Autowired
	private TableConverter tableConverter;

	@GetMapping("{id}")
	public TableDto get(@PathVariable("id") Long id) {
		return tableConverter.toDto(tableService.findById(id));
	}

}

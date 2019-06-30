package com.xyyh.edc.meta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dm.common.dto.TableResult;
import com.xyyh.edc.meta.converter.TableConverter;
import com.xyyh.edc.meta.dto.TableDto;
import com.xyyh.edc.meta.service.TableService;

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

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public TableDto update(@PathVariable("id") Long id, @RequestBody TableDto table) {
		return tableConverter.toDto(tableService.update(id, table));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TableDto save(@RequestBody TableDto table) {
		return tableConverter.toDto(tableService.save(table));
	}

	@GetMapping(params = { "draw" })
	public TableResult<TableDto> search(
			@RequestParam("draw") Long draw,
			@RequestParam(name = "keywords", required = false) String keywords,
			@PageableDefault(page = 0, size = 1, sort = "name", direction = Direction.ASC) Pageable pageable) {
		try {
			return TableResult.success(draw, tableService.find(keywords, pageable), tableConverter::toTableDto);
		} catch (Exception e) {
			return TableResult.failure(draw, pageable, e.getMessage());
		}
	}

}

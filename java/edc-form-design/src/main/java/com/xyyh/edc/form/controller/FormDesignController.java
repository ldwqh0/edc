package com.xyyh.edc.form.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xyyh.edc.common.dto.TableResult;
import com.xyyh.edc.form.converter.FormDefineConverter;
import com.xyyh.edc.form.dto.FormDefineDto;
import com.xyyh.edc.form.service.FormDefineService;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("forms")
public class FormDesignController {

	@Autowired
	private FormDefineService formDefineService;

	@Autowired
	private FormDefineConverter formDefineConverter;

	@PostMapping
	@ResponseStatus(CREATED)
	public FormDefineDto save(@RequestBody FormDefineDto form) {
		return formDefineConverter.toDto(formDefineService.save(form));
	}

	@PutMapping("{id}")
	@ResponseStatus(CREATED)
	public FormDefineDto update(@PathVariable("id") Long id, @RequestBody FormDefineDto form) {
		return formDefineConverter.toDto(formDefineService.update(id, form));
	}

	@GetMapping("{id}")
	public FormDefineDto findById(@PathVariable("id") Long id) {
		return formDefineConverter.toDto(formDefineService.findById(id));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void deleteById(@PathVariable("id") Long id) {
		formDefineService.deleteById(id);
	}

	@GetMapping(params = { "draw" })
	public TableResult<FormDefineDto> search(
			@RequestParam("draw") Long draw,
			@RequestParam(value = "keywords", required = false) String keywords,
			@PageableDefault(page = 0, size = 10) Pageable pageable) {
		return TableResult.success(draw, formDefineService.search(keywords, pageable), formDefineConverter::toTableDto);
	}
}

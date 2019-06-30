package com.xyyh.edc.data.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyyh.edc.data.service.EdcDataService;

@RestController
@RequestMapping("edc/datas")
public class DataController {

	@Autowired
	private EdcDataService dataService;

	@PostMapping("{id}")
	public Object save(@PathVariable("id") Long id, @RequestBody Map<String, Object> data) {
		dataService.save(id, data);
		return data;
	}
}

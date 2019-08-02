package com.xyyh.edc.data.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xyyh.edc.common.dto.TableResult;
import com.xyyh.edc.data.service.EdcDataService;
import com.xyyh.edc.meta.api.TableDefine;
import com.xyyh.edc.meta.service.TableService;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("datas")
public class DataController {

	@Autowired
	private EdcDataService dataService;

	@Autowired
	private TableService tableService;

	/**
	 * 将某个数据保存到某个数据集中
	 * 
	 * @param collection
	 * @param data
	 * @return
	 */
	@PostMapping("{collection}")
	@ResponseStatus(CREATED)
	public Object save(@PathVariable("collection") String collection, @RequestBody Map<String, Object> data) {
		Optional<TableDefine> tableDefine = tableService.findByName(collection);
		if (tableDefine.isPresent()) {
			return dataService.save(tableDefine.get(), data);
		} else {
			return null;
		}
	}

	@PutMapping("{collection}/{dataId}")
	public Object update(
			@PathVariable("collection") String collection,
			@PathVariable("dataId") String dataId,
			@RequestBody Map<String, Object> data) {
		Optional<TableDefine> tableDefine = tableService.findByName(collection);
		if (tableDefine.isPresent()) {
			return dataService.update(tableDefine.get(), dataId, data);
		} else {
			return null;
		}
	}

	/**
	 * 获取指定数据集，指定ID的数据
	 * 
	 * @param collection
	 * @param dataId
	 * @return
	 */
	@GetMapping("{collection}/{id}")
	public Object get(@PathVariable("collection") String collection, @PathVariable("id") String dataId) {
		Optional<TableDefine> tableDefine = tableService.findByName(collection);
		if (tableDefine.isPresent()) {
			return dataService.findOne(tableDefine.get(), dataId);
		} else {
			return null;
		}
	}

	/**
	 * 删除某个数据集的某条记录
	 * 
	 * @param collection
	 * @param dataId
	 */
	@DeleteMapping("{collection}/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable("collection") String collection, @PathVariable("id") String dataId) {
		Optional<TableDefine> tableDefine = tableService.findByName(collection);
		if (tableDefine.isPresent()) {
			dataService.deleteById(tableDefine.get(), dataId);
		}
	}

	/**
	 * 分页获取数据
	 * 
	 * @param collection
	 * @param draw
	 * @param pageable
	 * @return
	 */
	@GetMapping(value = "{collection}", params = "draw")
	public TableResult<Object> list(
			@PathVariable("collection") String collection,
			@RequestParam("draw") Long draw,
			@PageableDefault Pageable pageable) {
		Optional<TableDefine> tableDefine = tableService.findByName(collection);
		if (tableDefine.isPresent()) {
			return TableResult.success(draw, dataService.list(tableDefine.get(), pageable));
		} else {
			return TableResult.failure(draw, pageable, "指定数据定义不存在");
		}
	}
}
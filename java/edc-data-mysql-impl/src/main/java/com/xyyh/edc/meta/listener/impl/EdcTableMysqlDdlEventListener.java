package com.xyyh.edc.meta.listener.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.xyyh.edc.meta.dto.TableDto;
import com.xyyh.edc.meta.listener.DdlEventArgs;
import com.xyyh.edc.meta.listener.TableDdlEventListener;

@Component
public class EdcTableMysqlDdlEventListener implements TableDdlEventListener{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void beforeCreate(DdlEventArgs args) {
		TableDto dto = args.getOldData();
		try {
			jdbcTemplate.execute("create table edc_table_ (id_ bigint not null, name_ varchar(255), primary key (id_)) engine=InnoDB");
		}catch (Exception e) {
			e.printStackTrace();
		}
		args.setCancel(true);
	}

	@Override
	public void created(DdlEventArgs args) {
		
	}

	@Override
	public void createFailed(DdlEventArgs args) {
		
	}

	@Override
	public void beforeUpdate(DdlEventArgs args) {
		
	}

	@Override
	public void updated(DdlEventArgs args) {
		
	}

	@Override
	public void updateFailed(DdlEventArgs args) {
		
	}

	@Override
	public void beforeDelete(DdlEventArgs args) {
		
	}

	@Override
	public void deleted(DdlEventArgs args) {
		
	}

	@Override
	public void deleteFailed(DdlEventArgs args) {
		
	}

}

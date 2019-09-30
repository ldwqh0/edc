package com.xyyh.edc.meta.listener.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.xyyh.edc.meta.listener.DdlEventArgs;
import com.xyyh.edc.meta.listener.TableDdlEventListener;

@Service
public class EdcTableMysqlDdlEventListener implements TableDdlEventListener{

	@Autowired
	@Qualifier("mysqlJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void beforeCreate(DdlEventArgs args) {
		// TODO Auto-generated method stub
		System.out.println("beforeCreate");
	}

	@Override
	public void created(DdlEventArgs args) {
		// TODO Auto-generated method stub
		System.out.println("created");
	}

	@Override
	public void createFailed(DdlEventArgs args) {
		// TODO Auto-generated method stub
		System.out.println("createFailed");
	}

	@Override
	public void beforeUpdate(DdlEventArgs args) {
		// TODO Auto-generated method stub
		System.out.println("beforeUpdate");
	}

	@Override
	public void updated(DdlEventArgs args) {
		// TODO Auto-generated method stub
		System.out.println("updated");
	}

	@Override
	public void updateFailed(DdlEventArgs args) {
		// TODO Auto-generated method stub
		System.out.println("updateFailed");
	}

	@Override
	public void beforeDelete(DdlEventArgs args) {
		// TODO Auto-generated method stub
		System.out.println("beforeDelete");
	}

	@Override
	public void deleted(DdlEventArgs args) {
		// TODO Auto-generated method stub
		System.out.println("deleted");
	}

	@Override
	public void deleteFailed(DdlEventArgs args) {
		// TODO Auto-generated method stub
		System.out.println("deleteFailed");
	}

}

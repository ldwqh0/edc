package com.xyyh.edc.meta.listener.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.xyyh.edc.enums.MySqlColumnTypes;
import com.xyyh.edc.meta.api.ColumnDefine;
import com.xyyh.edc.meta.api.ColumnTypes;
import com.xyyh.edc.meta.listener.DdlEventArgs;
import com.xyyh.edc.meta.listener.TableDdlEventListener;

@Service
public class EdcTableMysqlDdlEventListener implements TableDdlEventListener{

	@Autowired
	@Qualifier("mysqlJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void beforeCreate(DdlEventArgs args) {
		//查询表是否存在
		String sql = "show tables like ?";
		String table;
		try {
			table = jdbcTemplate.queryForObject(sql, String.class, args.getOldData().getName());
		}catch (EmptyResultDataAccessException e) {
			table = null;
		}
		if(StringUtils.isNotBlank(table)) {
			args.setCancel(true);
		}
	}

	@Override
	public void created(DdlEventArgs args) {
		List<ColumnDefine> columns = args.getNewData().getColumns();
		String pk= "";
		
		StringBuffer sql = new StringBuffer("");
		sql.append("create table ");
		sql.append(args.getNewData().getName());
		sql.append(" (`data_id_` varchar(200) NOT NULL UNIQUE,");
		for (ColumnDefine column : columns) {
			sql.append("`" + column.getName() + "` " + MySqlColumnTypes.getEnum(column.getType()).getValue() + (column.getType().equals(ColumnTypes.STRING) || column.getType().equals(ColumnTypes.DECIMAL) || column.getType().equals(ColumnTypes.BINARY) ? "("+column.getLength()+")" : "") + " " + (column.isNullable() ? "" : "NOT NULL") + ",");
			if(column.isIdColumn()) {
				pk += (column.getName() + ",");
			}
		}
		if(StringUtils.isBlank(pk)) {
			sql.deleteCharAt(sql.length() - 1);
		}else {
			sql.append("PRIMARY KEY("+pk.substring(0, pk.length() - 1)+")");
		}
		sql.append(") engine=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC");
		jdbcTemplate.execute(sql.toString());
	}

	@Override
	public void createFailed(DdlEventArgs args) {
		jdbcTemplate.execute("drop table " + args.getNewData().getName());
	}

	@Override
	public void beforeUpdate(DdlEventArgs args) {
		System.out.println("beforeUpdate");
	}

	@Override
	public void updated(DdlEventArgs args) {
		System.out.println("updated");
	}

	@Override
	public void updateFailed(DdlEventArgs args) {
		System.out.println("updateFailed");
	}

	@Override
	public void beforeDelete(DdlEventArgs args) {
		//查询表是否存在
		String sql = "show tables like ?";
		String table;
		try {
			table = jdbcTemplate.queryForObject(sql, String.class, args.getOldData().getName());
		}catch (EmptyResultDataAccessException e) {
			table = null;
		}
		if(StringUtils.isBlank(table)) {
			args.setCancel(true);
		}else {
			//复制旧表创建语句，以便恢复
			String recoverySql = "show create table ?";
			SqlRowSet set = jdbcTemplate.queryForRowSet(recoverySql, args.getOldData().getName());
			args.setOldCreateSql(set.getString(2));
			//复制旧表数据，以便恢复
			String oldDataSql = "Select * From ?";
			List<Map<String, Object>> oldDatas = jdbcTemplate.queryForList(oldDataSql, args.getOldData().getName());
			args.setOldDatas(oldDatas);
		}
	}

	@Override
	public void deleted(DdlEventArgs args) {
		jdbcTemplate.execute("drop table " + args.getOldData().getName());
	}

	@Override
	public void deleteFailed(DdlEventArgs args) {
		//恢复表
		jdbcTemplate.execute(args.getOldCreateSql());
		//恢复数据
		List<String> sqls = new ArrayList<>();
		List<Map<String, Object>> oldDatas = args.getOldDatas();
		for (Map<String, Object> map : oldDatas) {
			String columns = "";
			String datas = "";
			for(Map.Entry<String, Object> m : map.entrySet()) {
				columns += (m.getKey() + ",");
				datas += ("'" + m.getValue() + "',"); 
			}
			sqls.add("Insert Into " + args.getOldData().getName() + "(" + columns.substring(0, columns.length() - 1) + ") Values (" + datas.substring(0, datas.length() - 1) + ")");
		}
		jdbcTemplate.batchUpdate(sqls.toArray(new String[sqls.size()]));
	}

}

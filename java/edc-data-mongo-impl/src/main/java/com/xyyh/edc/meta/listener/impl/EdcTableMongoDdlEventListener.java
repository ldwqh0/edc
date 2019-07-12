package com.xyyh.edc.meta.listener.impl;

import com.xyyh.edc.data.service.EdcDataService;
import com.xyyh.edc.meta.listener.DdlEventArgs;
import com.xyyh.edc.meta.listener.TableDdlEventListener;

public class EdcTableMongoDdlEventListener implements TableDdlEventListener {

	@Override
	public void beforeCreate(DdlEventArgs args) {
		args.getOldData();
		// TODO Auto-generated method stub

	}

	@Override
	public void created(DdlEventArgs args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createFailed(DdlEventArgs args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeUpdate(DdlEventArgs args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updated(DdlEventArgs args) {
		args.getOldData();// 获取创建之前的数据
		args.getNewData(); // 获取更新之后的数据
		args.setCancel(true); // 是否取消后续操做，如果设置为true,会取消后续的操作
		// TODO Auto-generated method stub

	}

	@Override
	public void updateFailed(DdlEventArgs args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeDelete(DdlEventArgs args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleted(DdlEventArgs args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteFailed(DdlEventArgs args) {
		// TODO Auto-generated method stub

	}

}

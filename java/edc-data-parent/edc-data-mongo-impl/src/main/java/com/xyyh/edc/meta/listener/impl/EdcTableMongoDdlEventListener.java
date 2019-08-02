package com.xyyh.edc.meta.listener.impl;

import org.springframework.stereotype.Component;

import com.xyyh.edc.meta.listener.DdlEventArgs;
import com.xyyh.edc.meta.listener.TableDdlEventListener;

@Component
public class EdcTableMongoDdlEventListener implements TableDdlEventListener {

	@Override
	public void beforeCreate(DdlEventArgs args) {
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

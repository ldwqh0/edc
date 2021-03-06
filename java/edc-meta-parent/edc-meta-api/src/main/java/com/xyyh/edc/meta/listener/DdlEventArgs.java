package com.xyyh.edc.meta.listener;

import java.io.Serializable;

import com.xyyh.edc.meta.api.TableDefine;

public class DdlEventArgs implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 在实际操作执行之前的元数据
	 */
	private TableDefine oldData;

	/**
	 * 在实际操作执行之后的元数据
	 */
	private TableDefine newData;

	/**
	 * 是否取消当前操作
	 */
	private boolean cancel;

	/**
	 * 如果发生错误时，相应的错误信息
	 */
	private Throwable thorwable;

	public TableDefine getOldData() {
		return oldData;
	}

	public void setOldData(TableDefine oldData) {
		this.oldData = oldData;
	}

	public TableDefine getNewData() {
		return newData;
	}

	public void setNewData(TableDefine newData) {
		this.newData = newData;
	}

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	public Throwable getThorwable() {
		return thorwable;
	}

	public void setThorwable(Throwable thorwable) {
		this.thorwable = thorwable;
	}

}

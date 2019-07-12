package com.xyyh.edc.meta.listener;

/**
 * 对元数据修改的监听器
 * 
 * @author LiDong
 *
 */
public interface TableDdlEventListener {
	/**
	 * 在一个新的集合被创建之前引发
	 */
	public void beforeCreate(DdlEventArgs args);

	/**
	 * 在一个新的集合创建成功之后引发
	 * 
	 * @param args
	 */
	public void created(DdlEventArgs args);

	/**
	 * 在一个激活创建失败之后引发
	 * 
	 * @param args
	 */
	public void createFailed(DdlEventArgs args);

	public void beforeUpdate(DdlEventArgs args);

	public void updated(DdlEventArgs args);

	public void updateFailed(DdlEventArgs args);

	public void beforeDelete(DdlEventArgs args);

	public void deleted(DdlEventArgs args);

	public void deleteFailed(DdlEventArgs args);
}

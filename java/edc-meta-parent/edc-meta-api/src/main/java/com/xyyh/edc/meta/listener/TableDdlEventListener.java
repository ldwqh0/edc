package com.xyyh.edc.meta.listener;

/**
 * 对元数据修改的监听器
 * 
 * @author LiDong
 *
 */
public interface TableDdlEventListener {
	/**
	 * 在一个新的集合（表）被创建之前引发
	 */
	public void beforeCreate(DdlEventArgs args);

	/**
	 * 在一个新的集合（表）创建成功之后引发
	 * 
	 * @param args
	 */
	public void created(DdlEventArgs args);

	/**
	 * 在一个集合（表）创建失败之后引发
	 * 
	 * @param args
	 */
	public void createFailed(DdlEventArgs args);

	/**
	 * 在一个集合(表)定义被更新之前引发
	 * 
	 * @param args
	 */
	public void beforeUpdate(DdlEventArgs args);

	/**
	 * 在一个集合(表)定义被更新之后引发
	 * 
	 * @param args
	 */
	public void updated(DdlEventArgs args);

	/**
	 * 在一个集合(表)定义被更新失败之后引发
	 * 
	 * @param args
	 */
	public void updateFailed(DdlEventArgs args);

	/**
	 * 在一个集合(表)定义被删除之前引发
	 * 
	 * @param args
	 */
	public void beforeDelete(DdlEventArgs args);

	/**
	 * 在一个集合(表)定义被删除之后引发
	 * 
	 * @param args
	 */
	public void deleted(DdlEventArgs args);

	/**
	 * 在一个集合(表)定义被删除失败之后引发
	 * 
	 * @param args
	 */
	public void deleteFailed(DdlEventArgs args);
}

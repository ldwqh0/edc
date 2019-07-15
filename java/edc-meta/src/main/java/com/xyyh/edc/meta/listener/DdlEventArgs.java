package com.xyyh.edc.meta.listener;

import java.io.Serializable;

import com.xyyh.edc.meta.dto.TableDto;

import lombok.Data;

@Data
public class DdlEventArgs implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 在实际操作执行之前的元数据
	 */
	private TableDto oldData;

	/**
	 * 在实际操作执行之后的元数据
	 */
	private TableDto newData;

	/**
	 * 是否取消当前操作
	 */
	private boolean cancel;

	/**
	 * 如果发生错误时，相应的错误信息
	 */
	private Throwable thorwable;

}

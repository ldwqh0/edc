package com.xyyh.edc.meta.exception;

/**
 * DDL操作被取消
 * 
 * @author LiDong
 *
 */
public class DdlCancelException extends RuntimeException {
	private static final long serialVersionUID = 3881445081580889709L;

	public DdlCancelException() {
		super();
	}

	public DdlCancelException(String message) {
		super(message);
	}
}

package com.anwei.entity.common;

import java.io.Serializable;

/**
 * 接口返回数据对象
 * 
 * @param <T> 业务对象
 */
public class GenericResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	private T data;

	public GenericResult(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
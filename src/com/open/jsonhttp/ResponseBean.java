package com.open.jsonhttp;

/**
 * 封装的数据响应包
 * 
 * @author yinghui.hong
 */
public abstract class ResponseBean {

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ResponseBean [status=" + status + "]";
	}

}

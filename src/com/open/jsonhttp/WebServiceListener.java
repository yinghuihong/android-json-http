package com.open.jsonhttp;

/**
 * 服务端接口响应监听类
 * 
 * @author yinghui.hong
 */
public interface WebServiceListener {
	/**
	 * 服务端响应监听事件
	 * 
	 * @param httpResCode
	 *            服务端响应码
	 * @param responseBean
	 *            封装的数据响应对象
	 */
	public abstract void onResponse(int httpResCode, ResponseBean responseBean);
}

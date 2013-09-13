package com.open.jsonhttp;


/**
 * 前端接口响应回调监听类(current only success and fail, maybe more in the future)
 * 
 * @author yinghui.hong
 */
public interface OnRequestHttpListener {
	/**
	 * 请求网络成功的回调行为（有返回JSON串，还需要判断Result值，通用1为Success，具体看各个接口文档）
	 * 
	 * @param responeKey
	 *            接口对应的索引值
	 * @param responseBean
	 *            响应数据的封装对象
	 */
	public void onSuccess(int responseKey, ResponseBean responseBean);

	/**
	 * 请求网络失败的回调行为（超时等异常）
	 * 
	 * @param responseKey
	 * @param code
	 */
	public void onFail(int responseKey, int code);
}

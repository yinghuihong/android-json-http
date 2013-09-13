package com.open.jsonhttp;

/**
 * HTTP请求响应代码
 * 
 * @author yinghui.hong
 */
public interface HttpResCode {
	/**
	 * 成功
	 */
	int SUCCESS = 200;

	/**
	 * 未开启网络
	 */
	int NETWORK_ERROR = -1;

	/**
	 * 服务端异常
	 */
	int SERVER_ERROR = -2;

	/**
	 * 网络连接超时
	 */
	int TIME_OUT = -3;

	/**
	 * JSON字符串解析错误
	 */
	int PARSE_JSON_ERROR = -4;

}

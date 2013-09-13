package com.open.jsonhttp;

import java.util.HashMap;

/**
 * 封装的请求包
 * 
 * @author yinghui.hong
 */
public abstract class RequestBean {
	/**
	 * 版本号
	 */
	private boolean sensor;

	public boolean isSensor() {
		return sensor;
	}

	public void setSensor(boolean sensor) {
		this.sensor = sensor;
	}

	/**
	 * 生成键值对，存放接口请求数据
	 * 
	 * @return
	 */
	public abstract HashMap<String, Object> generateMap();
}

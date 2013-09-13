package com.example.request;

import java.util.HashMap;

import com.example.constant.Request;
import com.open.jsonhttp.RequestBean;

/**
 * https://developers.google.com/maps/documentation/geocoding/?hl=zh-CN
 * <p>
 * http://maps.googleapis.com/maps/api/geocode/json?address=HK&sensor=true
 * <p>
 * geocode api 请求数据的封装类
 * 
 * @author yinghui.hong
 */
public class GeocodeReqBean extends RequestBean {

	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public HashMap<String, Object> generateMap() {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put(Request.sensor, isSensor());
		hashMap.put(Request.address, address);
		return hashMap;
	}

	@Override
	public String toString() {
		return "GeocodeReqBean [address=" + address + "]";
	}

}

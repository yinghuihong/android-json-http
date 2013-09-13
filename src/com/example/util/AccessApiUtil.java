package com.example.util;

import com.example.constant.Url;
import com.example.request.GeocodeReqBean;
import com.example.response.GeocodeResBean;
import com.open.jsonhttp.AsyncTask;
import com.open.jsonhttp.OnRequestHttpListener;
import com.open.jsonhttp.util.RequestUtil;

public class AccessApiUtil {

	public static final int INDEX_GEOCODE = 1;

	/**
	 * 跟定地址名称生成详细地址信息
	 * 
	 * @param address
	 * @param listener
	 * @return
	 */
	public static AsyncTask gencode(String address,
			final OnRequestHttpListener listener) {
		GeocodeReqBean requestBean = new GeocodeReqBean();
		requestBean.setSensor(true);
		requestBean.setAddress(address);
		return RequestUtil.request(listener, INDEX_GEOCODE, Url.GENCODE,
				requestBean, GeocodeResBean.class);// entry
	}
}

package com.open.jsonhttp.util;

import org.json.JSONException;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.open.jsonhttp.AsyncTask;
import com.open.jsonhttp.HttpResCode;
import com.open.jsonhttp.OnRequestHttpListener;
import com.open.jsonhttp.RequestBean;
import com.open.jsonhttp.RequestBeanFile;
import com.open.jsonhttp.ResponseBean;
import com.open.jsonhttp.WebServiceListener;

/**
 * 通讯请求工具类
 * 
 * @author yinghui.hong
 */
public class RequestUtil {

	private static final String TAG = RequestUtil.class.getSimpleName();

	/**
	 * @param url
	 * @param requestBean
	 * @param filePath
	 * @param classOfResponseBean
	 * @param webServiceListener
	 * @return
	 */
	private static AsyncTask request(String url, RequestBean requestBean,
			final Class<? extends ResponseBean> classOfResponseBean,
			final WebServiceListener webServiceListener) {
		if (NetwrokUtil.isConnected()) {
			// HandlerThread handlerThread = new
			// HandlerThread("Handler Thread");
			// handlerThread.start();
			// Handler handler = new Handler(handlerThread.getLooper()) {
			Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					super.handleMessage(msg);

					ResponseBean bean = null;
					if (msg.arg1 == HttpResCode.SUCCESS) {
						try {
							bean = ParseUtil.parseJson2Obj((String) msg.obj,
									classOfResponseBean);
						} catch (JSONException e) {
							webServiceListener.onResponse(
									HttpResCode.PARSE_JSON_ERROR, bean);
						}
					}
					webServiceListener.onResponse(msg.arg1, bean);
					Log.e("RequestUtil.java", "返回码 = " + msg.arg1);
				}
			};
			if (requestBean instanceof RequestBeanFile) {
				// 文件上传
				url += ParseUtil.parseMap2QueryStr(requestBean.generateMap());
				return ThreadUtil.doPost(url, handler,
						((RequestBeanFile) requestBean).generateFile());
			} else {
				// 例子https://developers.google.com/maps/documentation/geocoding/?hl=zh-CN
				// JSON串上传
				// return
				// ThreadUtil.doGet("http://maps.googleapis.com/maps/api/geocode/json?address=HK&sensor=true",
				// handler);
				// return
				// ThreadUtil.doGet(url+"?access_token=2.00xt1dIC0ncBIQ67e61cb6cbJNqHcD",
				// handler);
				// return ThreadUtil.doPost(url, handler,
				// ParseUtil.parseMap2JsonStr(requestBean.generateMap()));
				url += ParseUtil.parseMap2QueryStr(requestBean.generateMap());
				return ThreadUtil.doGet(url, handler);
			}

		} else {
			Log.i(TAG,
					"***********************network is error *************************\n");
			webServiceListener.onResponse(HttpResCode.NETWORK_ERROR, null);
			return null;
		}
	}

	/**
	 * @param onRequesthttplistener
	 * @param index
	 *            分辨接口的索引
	 * @param url
	 * @param requestBean
	 * @param filePath
	 *            if not null post the file ,others post the JSON string.
	 * @param classOfResponseBean
	 * @return
	 */
	public static AsyncTask request(
			final OnRequestHttpListener onRequesthttplistener, final int index,
			String url, RequestBean requestBean,
			final Class<? extends ResponseBean> classOfResponseBean) {
		final WebServiceListener webServiceListener = new WebServiceListener() {
			@Override
			public void onResponse(int httpResCode, ResponseBean responseBean) {
				switch (httpResCode) {
				case HttpResCode.SUCCESS:
					onRequesthttplistener.onSuccess(index, responseBean);
					break;
				case HttpResCode.NETWORK_ERROR:
					onRequesthttplistener.onFail(index,
							HttpResCode.NETWORK_ERROR);
					break;
				case HttpResCode.TIME_OUT:
					onRequesthttplistener.onFail(index, HttpResCode.TIME_OUT);
					break;
				case HttpResCode.SERVER_ERROR:
					onRequesthttplistener.onFail(index,
							HttpResCode.SERVER_ERROR);
					break;
				case HttpResCode.PARSE_JSON_ERROR:
					onRequesthttplistener.onFail(index,
							HttpResCode.PARSE_JSON_ERROR);
					break;
				default:
					onRequesthttplistener.onFail(index,
							HttpResCode.SERVER_ERROR);
					break;
				}
			}
		};
		return request(url, requestBean, classOfResponseBean,
				webServiceListener);
	}
}

package com.open.jsonhttp.util;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.open.jsonhttp.ResponseBean;

/**
 * 解析工具类
 * 
 * @author yinghui.hong
 */
public class ParseUtil {
	private static final String TAG = ParseUtil.class.getSimpleName();

	/**
	 * 转HashMap对象为Query字符串
	 * 
	 * @param valueMap
	 * @return
	 */
	public static String parseMap2QueryStr(HashMap<String, Object> valueMap) {
		StringBuilder paramsStr = new StringBuilder();
		if (valueMap != null && valueMap.size() > 0) {
			paramsStr.append("?");
			for (String key : valueMap.keySet()) {
				paramsStr.append(key).append("=").append(valueMap.get(key))
						.append("&");
			}
			paramsStr.deleteCharAt(paramsStr.length() - 1);
		}
		return paramsStr.toString();

	}

	/**
	 * 转HashMap对象为JSON字符串。
	 * 
	 * @param valueMap
	 *            键值对，存放接口请求数据
	 * @return String JSON字符串
	 */
	public static String parseMap2JsonStr(HashMap<String, Object> valueMap) {
		Gson gson = new Gson();
		String jsonStr = gson.toJson(valueMap);
		Log.d(TAG, "请求字符串 --- " + jsonStr);
		return jsonStr;
	}

	/**
	 * 解析JSON字符串为对象。
	 * 
	 * @param json
	 *            接口请求返回数据，JSON字符串
	 * @param classOfResponseBean
	 *            ResponseBean的子类
	 * @return ResponseBean的实例对象
	 * @throws JSONException
	 */
	public static ResponseBean parseJson2Obj(String json,
			Class<? extends ResponseBean> classOfResponseBean)
			throws JSONException {
		Log.d(TAG, "响应字符串 --- " + json);
		JSONObject jsonObject = new JSONObject(json);
		Gson gson = new Gson();
		ResponseBean bean = gson.fromJson(jsonObject.toString(),
				classOfResponseBean);
		return bean;
	}

}

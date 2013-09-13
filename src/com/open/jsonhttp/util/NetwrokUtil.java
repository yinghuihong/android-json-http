package com.open.jsonhttp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.App;

/**
 * 网络相关工具类
 * 
 * @author yinghui.hong
 */
public class NetwrokUtil {

	/**
	 * 无连接
	 */
	public static final int STATE_CONNECT_NONE = 0;

	/**
	 * WIFI连接
	 */
	public static final int STATE_CONNECT_WIFI = 1;

	/**
	 * 移动网络 2G/3G
	 */
	public static final int STATE_CONNECT_MOBILE = 2;

	/**
	 * 获取当前网络连接状态
	 * 
	 * @param context
	 * @return 常量 STATE_CONNECT_NONE：无连接， STATE_CONNECT_WIFI：WIFI连接,
	 *         STATE_CONNECT_MOBILE：移动网络 2G/3G
	 */
	public static int getNetConnectState(Context context) {
		final ConnectivityManager cm = (ConnectivityManager) App.context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (networkInfo != null && networkInfo.isConnected()) {
			return STATE_CONNECT_WIFI;
		}
		networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (networkInfo != null && networkInfo.isConnected()) {
			return STATE_CONNECT_MOBILE;
		}
		return STATE_CONNECT_NONE;
	}

	/**
	 * 判断网络是否连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnected() {
		final ConnectivityManager cm = (ConnectivityManager) App.context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		if (networkInfo == null || !networkInfo.isConnectedOrConnecting()) {
			return false;
		}
		return true;
	}

}

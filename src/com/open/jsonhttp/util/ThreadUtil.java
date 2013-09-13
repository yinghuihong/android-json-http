package com.open.jsonhttp.util;

import java.io.File;

import com.open.jsonhttp.AsyncTask;
import com.open.jsonhttp.HttpRequest;

import android.os.Handler;
import android.os.Message;

/**
 * <p>
 * 异步线程启动工具类
 * </p>
 * http://hi.baidu.com/wenfengyvn/item/79905fb737cf1077254b0904
 * http://www.android-study.com/jichuzhishi/376.html
 * 
 * @author yinghui.hong
 */
public class ThreadUtil {

	/**
	 * 开启线程，以GET方式请求
	 * 
	 * @param url
	 * @param handler
	 */
	protected static AsyncTask doGet(final String url, final Handler handler) {
		AsyncTask task = new AsyncTask() {
			@Override
			public void run() {
				Message message = HttpRequest.doGet(url);
				if (!Thread.currentThread().isInterrupted()) {
					handler.sendMessage(message);
					System.out.println("is running");
				} else {
					System.out.println("is interrupted");
				}
			}
		};
		task.start();
		return task;
	}

	/**
	 * 开启线程，以POST方式进行文件上传
	 * 
	 * @param url
	 * @param handler
	 * @param file
	 *            文件
	 * @return
	 */
	protected static AsyncTask doPost(final String url, final Handler handler,
			final File file) {
		AsyncTask task = new AsyncTask() {
			@Override
			public void run() {
				Message message = HttpRequest.doPost(url, file);
				if (!Thread.currentThread().isInterrupted()) {
					handler.sendMessage(message);
					System.out.println("is running");
				} else {
					System.out.println("is interrupted");
				}
			}
		};
		task.start();
		return task;
	}

	/**
	 * 开启线程，以POST方式JSON串请求
	 * 
	 * @param url
	 * @param handler
	 * @param requestBody
	 *            JSON串
	 * @return
	 */
	protected static AsyncTask doPost(final String url, final Handler handler,
			final String requestBody) {
		AsyncTask task = new AsyncTask() {
			@Override
			public void run() {
				Message message = HttpRequest.doPost(url, requestBody);
				if (!Thread.currentThread().isInterrupted()) {
					handler.sendMessage(message);
					System.out.println("is running");
				} else {
					System.out.println("is interrupted");
				}

			}
		};
		task.start();
		return task;
	}

}

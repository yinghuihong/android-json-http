package com.open.jsonhttp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.Message;
import android.util.Log;

/**
 * HTTP请求执行类
 * 
 * @author yinghui.hong
 */
public class HttpRequest {

	private static final String TAG = HttpRequest.class.getSimpleName();

	private static final int TIMEOUT = 3000;

	private static final String ENCODEING = HTTP.UTF_8;

	/**
	 * 以GET方式请求
	 * 
	 * @param url
	 *            接口地址
	 * @return Message对象
	 */
	public static Message doGet(String url) {
		Log.i(TAG, "GET:REQUEST URL IS --- " + url);
		HttpGet httpGet = new HttpGet(url);
		return executeRequest(httpGet);
	}

	/**
	 * 以POST方式请求（上传文件）
	 * 
	 * @param url
	 *            接口地址
	 * @param file
	 *            上传的文件
	 * @return Message对象
	 */
	public static Message doPost(String url, final File file) {
		Log.i(TAG, "POST:REQUEST URL IS --- " + url);
		HttpPost httpPost = new HttpPost(url);
		FileEntity entity = new FileEntity(file, "binary/octet-stream");
		httpPost.setEntity(entity);
		return executeRequest(httpPost);
	}

	/**
	 * 以POST方式请求（上传JSON串）
	 * 
	 * @param url
	 *            接口地址
	 * @param requestBody
	 *            发送的请求数据包 JSON串
	 * @return Message对象
	 */
	public static Message doPost(String url, final String requestBody) {
		Log.i(TAG, "POST:REQUEST URL IS --- " + url);
		HttpPost httpPost = new HttpPost(url);
		ContentProducer contentProducer = new ContentProducer() {
			public void writeTo(OutputStream outstream) throws IOException {
				Writer writer = new OutputStreamWriter(outstream, ENCODEING);
				writer.write(requestBody);
				writer.flush();
			}
		};
		HttpEntity entity = new EntityTemplate(contentProducer);
		httpPost.setEntity(entity);
		return executeRequest(httpPost);
	}

	/**
	 * 执行请求
	 * 
	 * @param httpUrlRequest
	 *            the request to execute
	 * @return Message 对象
	 */
	private static Message executeRequest(HttpUriRequest httpUrlRequest) {

		HttpClient httpClient = new DefaultHttpClient();
		Message message = Message.obtain();
		try {
			httpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, TIMEOUT);
			httpClient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, TIMEOUT);
			HttpResponse httpResponse = httpClient.execute(httpUrlRequest);
			int responseCode = httpResponse.getStatusLine().getStatusCode();
			HttpEntity httpEntity = httpResponse.getEntity();
			message.arg1 = responseCode;
			if (responseCode == HttpStatus.SC_OK) {
				message.obj = EntityUtils.toString(httpEntity, ENCODEING);
			}
		} catch (ConnectTimeoutException e) {
			// 包括请求超时和响应超时
			Log.e(TAG,
					"******************ConnectTimeoutException****************\n"
							+ e.toString());
			message.arg1 = HttpResCode.TIME_OUT;
		} catch (Exception e) {
			// IOException/ParseException/ClientProtocolException统一规为服务器异常
			Log.e(TAG, "*********************ServerError ******************\n"
					+ e.toString());
			message.arg1 = HttpResCode.SERVER_ERROR;
		} finally {
			// 关闭连接，释放资源
			httpClient.getConnectionManager().shutdown();
		}
		return message;
	}

	/**
	 * GET方式，获取输入流
	 * 
	 * @param uri
	 * @return
	 * @throws ConnectTimeoutException
	 * @throws IOException
	 */
	public static InputStream getInputStream(String uri)
			throws ConnectTimeoutException, IOException {
		InputStream inputStream = null;
		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, TIMEOUT);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				TIMEOUT);
		HttpGet httpGet = new HttpGet(uri);
		HttpResponse httpResponse = httpClient.execute(httpGet);
		int responseCode = httpResponse.getStatusLine().getStatusCode();
		HttpEntity httpEntity = httpResponse.getEntity();
		if (responseCode == HttpStatus.SC_OK) {
			inputStream = httpEntity.getContent();
		}
		return inputStream;
	}
}

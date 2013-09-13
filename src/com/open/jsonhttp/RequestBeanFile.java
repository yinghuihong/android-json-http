package com.open.jsonhttp;

import java.io.File;

/**
 * 封装需要上传文件的请求包
 * 
 * @author yinghui.hong
 */
public abstract class RequestBeanFile extends RequestBean {
	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public abstract File generateFile();
}

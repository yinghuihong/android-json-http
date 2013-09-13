package com.open.jsonhttp.util;

import java.util.Random;

/**
 * 字符串工具类
 * 
 * @author yinghui.hong
 */
public class StringUtil {
	private static final char[] SOURCE = "123456789abcdefghijklmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ"
			.toCharArray();

	// FUNO FIX OK 看一下下面两个方法是否可以去掉一个
	/**
	 * 获取随机字符串。
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomStr(int length) {
		if (length < 1) {
			return null;
		}
		Random random = new Random();
		char[] buf = new char[length];
		int index = 0;
		for (int i = 0; i < length; i++) {
			index = random.nextInt(SOURCE.length);
			buf[i] = SOURCE[index];
		}
		return new String(buf);
	}

}

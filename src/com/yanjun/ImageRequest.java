package com.yanjun;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageRequest {

	/**
	 * 通过HTTP协议请求获取网页数据： http://www.baidu.com/
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// 从网络上获取图片--URL对象用来封装路径
		URL url = new URL("http://www.baidu.com/");
		// 打开路径链接---得到HttpURLConnection对象
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		// 通过HTTP协议请求网络html---设置请求方式：get/post
		httpURLConnection.setRequestMethod("GET");
		// 设置连接超时
		httpURLConnection.setConnectTimeout(5000);
		// 从外界想手机应用内传递数据----通过输入流获取html数据
		InputStream inputStream = httpURLConnection.getInputStream();
		// 从输入流中获取html的二进制数据----readInputStream()
		byte[] data = readInputStream(inputStream);
		// 将HTML代码的二进制转换成string类型
		String html = new String(data);
		// 将数据打印在控制台上
		System.out.println(html);
		System.out.println(data);
		inputStream.close();
	}

	// 读取输入流的方法
	public static byte[] readInputStream(InputStream inSream) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		// 定义一个缓冲区
		byte[] buffer = new byte[1024];
		int len = 0;
		// 不断的从流里读取数据---while循环---nSream.read(buffer)表示从流里读取数据到缓冲区
		// 读取到末尾时，返回值是-1；
		while ((len = inSream.read(buffer)) != -1) {
			// 将缓冲区的数据写到输出流中
			byteArrayOutputStream.write(buffer, 0, len);
		}
		inSream.close();
		return byteArrayOutputStream.toByteArray();
	}

}
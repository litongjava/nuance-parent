package com.nte.realtime;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HTTPClient {
	
	private static Map<String,CloseableHttpClient> httpClient = new HashMap<>();
	//从连接池中获取连接超时
	//连接超时,读取超时
	private static RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(10000).setSocketTimeout(600000).build();

	private final static Object syncLock = new Object();
	
	public static CloseableHttpClient getHttpClient(String url) {
		// http://www.baidu.com/
		// [0] http:
		// [1] 
		//
		String hostname = url.split("/")[2];
		int port = 80;
		if (hostname.contains(":")) {
			String[] arr = hostname.split(":");
			hostname = arr[0];
			port = Integer.parseInt(arr[1]);
		}
		String key = hostname+":"+port;
		if (!httpClient.containsKey(key)) {
			synchronized (syncLock) {
				if (!httpClient.containsKey(key)) {
					httpClient.put(key,createHttpClient(200, 40, 100, hostname, port));
				}
			}
		}
		return httpClient.get(key);
	}
	/**
	 * 创建HttpClient对象
	 * 
	 * @create 2015年12月18日
	 */
	public static CloseableHttpClient createHttpClient(int maxTotal,int maxPerRoute, int maxRoute, String hostname, int port) {
		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
		LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", plainsf).register("https", sslsf).build();
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		cm.setMaxTotal(maxTotal);
		cm.setDefaultMaxPerRoute(maxPerRoute);
		HttpHost httpHost = new HttpHost(hostname, port);
		cm.setMaxPerRoute(new HttpRoute(httpHost), maxRoute);
		return HttpClients.custom().setConnectionManager(cm).build();
	}
	
	public static String get(String url) throws IOException{
		HttpGet httpget = new HttpGet(url);
		httpget.setConfig(requestConfig);
		return request(url, httpget);
	}
	
	private static String request(String url,HttpUriRequest request) throws IOException{
		CloseableHttpResponse response = null;
		HttpEntity entity  = null;
		try {
			response = getHttpClient(url).execute(request,HttpClientContext.create());
			entity = response.getEntity();
			String body = EntityUtils.toString(entity,"UTF-8");
			if(response.getStatusLine().getStatusCode()!=HttpStatus.SC_OK){
				throw new IOException(body);
			}
			return body;
		}  finally {
			try {
				if(entity!=null){
					EntityUtils.consume(entity);
				}
				if (response != null)
					response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String put(String url,Map<String,String> headers,HttpEntity requestEntity) throws IOException{
		HttpPut httpPut = new HttpPut(url);
		httpPut.setConfig(requestConfig);
		httpPut.setEntity(requestEntity);
		for(Entry<String,String> entry : headers.entrySet()){
			httpPut.setHeader(entry.getKey(), entry.getValue());
		}
		return request(url, httpPut);
	}
	
	public static String put(String url,HttpEntity requestEntity) throws IOException{
		HttpPut httpPut = new HttpPut(url);
		httpPut.setConfig(requestConfig);
		httpPut.setEntity(requestEntity);
		return request(url, httpPut);
	}
	
	public static String post(String url,HttpEntity requestEntity) throws IOException{
		HttpPost httppost = new HttpPost(url);
		httppost.setConfig(requestConfig);
		httppost.setEntity(requestEntity);
		return request(url, httppost);
	}
}

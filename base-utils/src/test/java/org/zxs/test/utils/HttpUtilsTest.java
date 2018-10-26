package org.zxs.test.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.zxs.utils.CommonUtil;
import org.zxs.utils.HttpUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest({Map.class, DefaultHttpClient.class, SchemeRegistry.class, Scheme.class, X509TrustManager.class, ByteArrayEntity.class, HttpPut.class, HttpPost.class, HttpParams.class,
//	SSLSocketFactory.class, HttpUtils.class, HttpDelete.class, HttpResponse.class, HttpClient.class, ClientConnectionManager.class,SSLContext.class, HttpGet.class })
public class HttpUtilsTest {
	 
	private static final String HOST = "https://host";
	private static final String PATH = "path";
	
	@Test
	public void testPost() throws Exception{
//		Map<String, String> headers = new HashMap<>();
//		
//		Map<String, String> querys = new HashMap<>();
//		querys.put("username", "tb");
//		querys.put("password", "b0baee9d279d34fa1dfd71aadb908c3f");
//		
//		HttpResponse response = HttpUtils.doPost("http://116.10.194.123:8808/leader-control-web", "/normal-login", headers, querys, "");
//		System.out.println(EntityUtils.toString(response.getEntity()));
		
//		Map<String, String> headers = new HashMap<>();
//		
//		Map<String, String> querys = new HashMap<>();
////		querys.put("projno", "2017-450126-54-03-019296");
//		querys.put("_", System.currentTimeMillis() + 8*60*60*1000 + "");
//		
//		
////		HttpResponse response = HttpUtils.doPost("http://202.103.199.210:8070/nnxmgl/foreignrest/nnForeignService/getProjs", "", headers, querys, "");
//			
//		HttpResponse response = HttpUtils.doPost("http://d1.weather.com.cn/sk_2d/101301102.html", "", headers, querys, "");
//		
//		
////		HttpResponse response = HttpUtils.doGet("http://202.103.199.210:8070/nnxmgl/foreignrest/nnForeignService", "/getProjCount", headers, querys);
//		System.out.println(EntityUtils.toString(response.getEntity()));
//		System.out.println(response);
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", "bearer fd061684-865d-4023-a985-3992d5a1eb6c");
		
		Map<String, String> querys = new HashMap<>();
		querys.put("groupID", "63402");
		querys.put("shopID", "76100208");
		querys.put("reportDate", "20181016");
//		querys.put("shopID", "10090088");
		
		
//		HttpResponse response = HttpUtils.doPost("http://202.103.199.210:8070/nnxmgl/foreignrest/nnForeignService/getProjs", "", headers, querys, "");
			
//		HttpResponse response = HttpUtils.doPost("http://open-api.hualala.com/rlj/getPayInfoByReportDate", "", headers, querys, "");
		
		HttpResponse response = HttpUtils.doPost("http://open-api.hualala.com/rlj/getOrderFoodAndPayInfoByReportDate", "", headers, querys, "");
		
//		HttpResponse response = HttpUtils.doGet("http://202.103.199.210:8070/nnxmgl/foreignrest/nnForeignService", "/getProjCount", headers, querys);
		System.out.println(EntityUtils.toString(response.getEntity()));
//		System.out.println(response);
		
	}
	
	@Test
	public void testImpPost() throws Exception{
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", "443fe2a0907f4492969280ada38e835c");
		
		Map<String, String> querys = new HashMap<>();
		querys.put("cardLevel", "2");
			
		HttpResponse response = HttpUtils.doPost("http://10.18.104.146:8911/imp-task-web/card-task-page", "", headers, querys, "");
		
		System.out.println(EntityUtils.toString(response.getEntity()));
		System.out.println(response);
	}
	
	@Test
	public void testAuthPost() throws Exception{
		String auth = "gxspw" + ":" + "XrhaOUdcSHbv";
	    String authHeader = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
	    
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", authHeader);
		
		Map<String, String> querys = new HashMap<>();
		querys.put("scope", "read");
		querys.put("grant_type", "client_credentials");
			
		HttpResponse response = HttpUtils.doPost("http://auth.hualala.com/oauth/token", "", headers, querys, "");
		
		System.out.println(EntityUtils.toString(response.getEntity()));
		System.out.println(response);
	}
	
	@Test
	public void testGet() throws Exception{
		
		Map<String, String> headers = new HashMap<>();
		
		Map<String, String> querys = new HashMap<>();
		querys.put("interfaceId", "GetRffcForFGW");
		querys.put("Longitude", "107.2");
		querys.put("Latitude", "23.6");
		querys.put("dataType", "html");
			
		HttpResponse response = HttpUtils.doGetwithTimeout("http://113.12.66.181:8007","/SWDataservice/interface/v1/api", headers, querys, 30000);
		
		System.out.println(EntityUtils.toString(response.getEntity()));
		System.out.println(response);
		
	}
	
	@Test
	public void testPostJSONString() throws Exception{
		Map<String, String> headers = new HashMap<>();
		
		JSONObject pi = new JSONObject();
		pi.put("id", 21136);
		pi.put("content", "bbbbbbbbbbbbbbbbbbbbbbbb");
		
		Map<String, String> querys = new HashMap<>();
		querys.put("ldPrj", JSON.toJSONString(pi));
		
		HttpResponse response = HttpUtils.doPost("http://localhost:8080/leader-control-web/admin/ld-prj-update-whole/21136", "", headers, querys, "");
		
		System.out.println(EntityUtils.toString(response.getEntity()));
		System.out.println(response);
	}
	
	@Test
	public void testPostJSON() throws Exception{
//		JSONObject json = new JSONObject();
//		json.put("projid", "6b32c3b0-828a-4393-884b-ef05b23bfe3");
//		
//		String url = "http://202.103.199.210:8070/nnxmgl/foreignrest/nnForeignService/getProgressCjList";
//		String result = HttpUtils.doPost(json, url);
//		System.out.println(result);
		
//		JSONObject json = new JSONObject();
//		json.put("projno", "2017-450126-54-03-019296");
//		System.out.println(JSON.toJSONString(json));
//		
//		String url = "http://202.103.199.210:8070/nnxmgl/foreignrest/nnForeignService/getProjs";
//		String result = HttpUtils.doPost(json, url);
//		System.out.println("请求接口地址：" + url);
//		System.out.println(result);
		
//		String prgr = "9c06a229-2425-48a9-9865-a4b71e867caa";
//		JSONObject jsonPrgr = new JSONObject();
//		jsonPrgr.put("projid", prgr);
//		String resultPrgr = HttpUtils.doPost(jsonPrgr, "http://202.103.199.210:8070/nnxmgl/foreignrest/nnForeignService/getProgressCjList");
//		System.out.println(resultPrgr);
		
		JSONObject jsonPrgr = new JSONObject();
		jsonPrgr.put("grant_type", "client_credentials");
		jsonPrgr.put("scope", "read");
		String resultPrgr = HttpUtils.doPost(jsonPrgr, "http://auth.hualala.com/oauth/token");
		System.out.println(resultPrgr);
		
	}
	
//	@Test
	public void testBuildUrl() throws Exception{
	
		Map<String,String> map = new HashMap<String,String>();
		map.put("k1", "v1");
		map.put(null, "key-null");
		HttpUtils hp = new HttpUtils();
		Class<HttpUtils> c = HttpUtils.class;   
		Method method = c.getDeclaredMethod("buildUrl", new Class[]{String.class, String.class, Map.class});
		method.setAccessible(true);
		Object object = method.invoke(hp, new Object[]{HOST, PATH, map});
		assertEquals("https://hostpath?key-null&k1=v1", object.toString());
		System.out.println("Test: HttpUtils.buildUrl()= "+object);
		 
	}
	
//	@Test
	public void testDoDeleteAndTOPutAndDoPostAndDoGet() throws Exception{
		//--
		DefaultHttpClient httpClient = PowerMockito.mock(DefaultHttpClient.class);
		PowerMockito.whenNew(DefaultHttpClient.class).withAnyArguments().thenReturn(httpClient);
		SSLContext ssl = PowerMockito.mock(SSLContext.class);
		PowerMockito.mockStatic(SSLContext.class);
		PowerMockito.when(SSLContext.getInstance(Mockito.anyString())).thenReturn(ssl);
		
	//	X509TrustManager x509 = PowerMockito.mock(X509TrustManager.class);
	//	PowerMockito.when(x509.getAcceptedIssuers()).thenReturn(null);
		
		SSLSocketFactory ssls = PowerMockito.mock(SSLSocketFactory.class);
		PowerMockito.whenNew(SSLSocketFactory.class).withAnyArguments().thenReturn(ssls);
		
		ClientConnectionManager client = PowerMockito.mock(ClientConnectionManager.class);
		PowerMockito.when(httpClient.getConnectionManager()).thenReturn(client);
		SchemeRegistry scheme = PowerMockito.mock(SchemeRegistry.class);
		PowerMockito.when(client.getSchemeRegistry()).thenReturn(scheme);
		
		Scheme sch = PowerMockito.mock(Scheme.class);
		PowerMockito.whenNew(Scheme.class).withAnyArguments().thenReturn(sch);
	//--
		CloseableHttpResponse httpResponse = PowerMockito.mock(CloseableHttpResponse.class);
		
		//HttpUtils.doDelete()
		HttpDelete request = PowerMockito.mock(HttpDelete.class);
		PowerMockito.whenNew(HttpDelete.class).withAnyArguments().thenReturn(request);
		PowerMockito.when(httpClient.execute(request)).thenReturn(httpResponse);
		String method = "method";
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("k1", "v1");
		Map<String,String> querys = new HashMap<String,String>();
		querys.put("k2", "v2"); 
		querys.put(null,"key-null");
		HttpResponse response = HttpUtils.doDelete(HOST, PATH, method, headers, querys); 
		assertNotNull(response);
		System.out.println("Test: HttpUtils.doDelete()："+response);
		
		//HttpUtils.doput()
		ByteArrayEntity byteArray = PowerMockito.mock(ByteArrayEntity.class);
		PowerMockito.whenNew(ByteArrayEntity.class).withAnyArguments().thenReturn(byteArray);
		HttpPut httpPutRequest = PowerMockito.mock(HttpPut.class);
		PowerMockito.whenNew(HttpPut.class).withAnyArguments().thenReturn(httpPutRequest);
		PowerMockito.when(httpClient.execute(httpPutRequest)).thenReturn(httpResponse);
		byte[] bodys =new byte[2];
		response = HttpUtils.doPut(HOST, PATH, method, headers, querys, bodys);
		assertNotNull(response);
		System.out.println("Test: HttpUtils.doput1()："+response);
		
		//HttpUtils.doput()
		String body = "测试";
		response = HttpUtils.doPut(HOST, PATH, method, headers, querys, body);
		assertNotNull(response);
		System.out.println("Test: HttpUtils.doput2()："+response);
		
		//HttpUtils.doPost()
		HttpPost httpPostRequest = PowerMockito.mock(HttpPost.class);
		PowerMockito.whenNew(HttpPost.class).withAnyArguments().thenReturn(httpPostRequest);
		PowerMockito.when(httpClient.execute(httpPostRequest)).thenReturn(httpResponse);
		
		response = HttpUtils.doPost(HOST, PATH, headers, querys, bodys);
		assertNotNull(response);
		System.out.println("Test: HttpUtils.doPost1()："+response);
		
		response = HttpUtils.doPost(HOST, PATH, headers, querys, body);
		assertNotNull(response);
		System.out.println("Test: HttpUtils.doPost2()："+response);
		
		Map<String, String> bodysMap = new HashMap<>();
		bodysMap.put("k3", "v3");
		response = HttpUtils.doPost(HOST, PATH, headers, querys, bodysMap);
		assertNotNull(response);
		System.out.println("Test: HttpUtils.doPost3()："+response);
		
		//HttpUtils.doGetwithTimeout()
		HttpGet httpGet = PowerMockito.mock(HttpGet.class);
		PowerMockito.whenNew(HttpGet.class).withAnyArguments().thenReturn(httpGet);
		PowerMockito.when(httpClient.execute(httpGet)).thenReturn(httpResponse);
	 	HttpParams httpParam = PowerMockito.mock(HttpParams.class);
	 	PowerMockito.when(httpClient.getParams()).thenReturn(httpParam);
		int timeOut = 10;
		response = HttpUtils.doGetwithTimeout(HOST, PATH, headers, querys, timeOut);
		assertNotNull(response);
		System.out.println("Test: HttpUtils.doGetwithTimeout():"+response);
		
		//HttpUtils.doGet()
		response = HttpUtils.doGet(HOST, PATH, headers, querys);
		assertNotNull(response);
		System.out.println("Test: HttpUtils.doGet():"+ response);
		
	}
	 
//	@Test
	public void testSslClientAndWrapClient() throws Exception{
		HttpUtils hu = PowerMockito.mock(HttpUtils.class);
		assertNotNull(hu);
		
		DefaultHttpClient httpClient = PowerMockito.mock(DefaultHttpClient.class);
		PowerMockito.whenNew(DefaultHttpClient.class).withAnyArguments().thenReturn(httpClient);
		SSLContext ssl = PowerMockito.mock(SSLContext.class);
		PowerMockito.mockStatic(SSLContext.class);
		PowerMockito.when(SSLContext.getInstance(Mockito.anyString())).thenReturn(ssl);
		
	//	X509TrustManager x509 = PowerMockito.mock(X509TrustManager.class);
	//	SSLSocketFactory ssls = PowerMockito.mock(SSLSocketFactory.class);
	//	PowerMockito.whenNew(SSLSocketFactory.class).withAnyArguments().thenReturn(ssls);
		
		ClientConnectionManager client = PowerMockito.mock(ClientConnectionManager.class);
		PowerMockito.when(httpClient.getConnectionManager()).thenReturn(client);
		SchemeRegistry scheme = PowerMockito.mock(SchemeRegistry.class);
		PowerMockito.when(client.getSchemeRegistry()).thenReturn(scheme);
		
		Scheme sch = PowerMockito.mock(Scheme.class);
		PowerMockito.whenNew(Scheme.class).withAnyArguments().thenReturn(sch);
	
		//测试HttpUtils.sslClient()
		HttpUtils httpUtils = new HttpUtils();
		Class<HttpUtils> c = HttpUtils.class;
		Method method = c.getDeclaredMethod("sslClient", new Class[]{HttpClient.class});
		method.setAccessible(true);
		Object result = method.invoke(httpUtils, new HttpClient[]{httpClient});
		assertNull(result);
		System.out.println("Test: HttpUtils.void.sslClient():"+result);
		
	    //测试HttpUtils.wrapClient()
		Method method2 = c.getDeclaredMethod("wrapClient", new Class[]{String.class});
		method2.setAccessible(true);
		Object result2 = method2.invoke(httpUtils, new String[]{HOST});
		assertNotNull(result2);
		System.out.println("Test: HttpUtils.wrapClient():"+result2);
		 
	}

}

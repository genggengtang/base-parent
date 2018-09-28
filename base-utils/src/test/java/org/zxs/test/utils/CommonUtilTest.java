package org.zxs.test.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.zxs.utils.CommonUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

@RunWith(PowerMockRunner.class)
@PrepareForTest({URLConnection.class,URL.class,byte[].class,ByteArrayOutputStream.class,InputStream.class,CommonUtil.class,Thread.class,String.class,URLEncoder.class,FileUtils.class})
public class CommonUtilTest {

	@Test
	public void testDoGet() throws IOException{
//		String content = CommonUtil.doGetUrl("https://www.jyblue.com/drc-web/wrd-reservoir-list", 90000);
//		String content = CommonUtil.doGetUrl("http://d1.weather.com.cn/sk_2d/101301102.html?_=" + System.currentTimeMillis(), 90000);
//		JSONArray contentJson = JSONArray.parseArray(content);
//		assertNotNull(contentJson);
//		assertTrue(contentJson.get(0).toString().contains("area"));
		
//		String content = CommonUtil.doGetUrl("http://113.12.66.181:8007/SWDataservice/interface/v1/api?interfaceId=GetRffcForFGW&Longitude=107.2&Latitude=23.6&dataType=html", 30000);
		String content = CommonUtil.doGetUrl("http://172.16.124.192:7081/get-aqi-info", 30000);
//		JSONArray contentJson = JSONArray.parseArray(content);
		System.out.println(JSON.toJSONString(content));
	}
	
	@Test
	public void testHttpsRequest(){
		//异常测试
		JSONArray json = CommonUtil.httpsRequest("ww.12.c");
		assertNotNull(json);
		assertTrue(json.isEmpty());
		//正常测试
		JSONArray ja = CommonUtil.httpsRequest("https://www.jyblue.com/drc-web/wrd-reservoir-list");
		assertNotNull(ja);
		boolean flag = false;
		for(int i=0;i<ja.size();i++){
			if(!ja.getJSONObject(i).get("name").toString().isEmpty()){
				flag=true;
				break;
			}
		}
		assertTrue(flag);
	}
			
	@Test
	public void testDoGetUrl() throws Exception {
		String resultString = CommonUtil.doGetUrl("https://www.jyblue.com/drc-web/wrd-reservoir-list").toString();
		assertTrue(resultString.contains("area"));
	}
	
	@Test
	public void testStringHttpsRequest(){
		//异常测试
		String str = CommonUtil.stringHttpsRequest("ww.12.c");
		assertEquals(str, "");
		//正常测试
		String resultString = CommonUtil.stringHttpsRequest("https://www.jyblue.com/drc-web/wrd-reservoir-list").toString();
		assertTrue(resultString.contains("area"));
	}
	public void testpublic(){
		
	}
	
	@Test
	public void testGetHttpResponseWithCharset() throws Exception{
		URL url = PowerMockito.mock(URL.class);
		PowerMockito.whenNew(URL.class).withArguments(anyString()).thenReturn(url);
		URLConnection connection = PowerMockito.mock(URLConnection.class);
		PowerMockito.when(url.openConnection()).thenReturn(connection);
		Map<String, List<String>> map = PowerMockito.mock(HashMap.class);
		PowerMockito.when(connection.getHeaderFields()).thenReturn(map);
		
		InputStream in = PowerMockito.mock(InputStream.class);
		PowerMockito.when(connection.getInputStream()).thenReturn(in );
		BufferedReader br = PowerMockito.mock(BufferedReader.class);
		PowerMockito.whenNew(BufferedReader.class).withAnyArguments().thenReturn(br);
		PowerMockito.when(br.readLine()).thenReturn("1").thenReturn(null);
		String jsapiUrl = "dd"; 
		String charSet = "utf-8";
		String result = CommonUtil.getHttpResponseWithCharset(jsapiUrl, charSet);
		assertEquals("1", result.trim());
	}
/*	
	@Test
	public void testGetHttpResponseWithCharset() throws Exception{
		String resultString = CommonUtil.getHttpResponseWithCharset("https://www.jyblue.com/drc-web/wrd-reservoir-list", "utf-8",60000);
		assertTrue(resultString.contains("歪甲水库"));
	}*/
/*	
	@Test
	public void testMoreGetHttpResponseWithCharset() throws Exception{
		String resultString = CommonUtil.getHttpResponseWithCharset("https://www.jyblue.com/drc-web/wrd-reservoir-list", "utf-8");
		assertTrue(resultString.contains("歪甲水库"));
	}
*/	
/*	@Test
	public void testGetHttpStream() throws Exception{
		InputStream input = CommonUtil.getHttpStream("https://www.jyblue.com/drc-web/wrd-reservoir-list");
		assertNotNull(input);
		StringBuffer sb = new StringBuffer();
		byte[] buf = CommonUtil.input2byte(input);
		sb.append(new String(buf,"UTF-8"));
		input.close();
		assertTrue(sb.toString().contains("歪甲水库"));
	}*/
	
	@Test
	public void testGetHttpStream() throws Exception{
		InputStream in = PowerMockito.mock(InputStream.class);
		URL url = PowerMockito.mock(URL.class);
		PowerMockito.whenNew(URL.class).withArguments(anyString()).thenReturn(url);
		URLConnection connection = PowerMockito.mock(URLConnection.class);
		PowerMockito.when(url.openConnection()).thenReturn(connection);
		PowerMockito.when(connection.getInputStream()).thenReturn(in );
		
		InputStream input = CommonUtil.getHttpStream("dd");
		assertNotNull(input);
		
		
	}
	
	
	
	@Test
	public void testInput2byte() throws Exception{
		byte[] buff = {1,2};
		buff = Matchers.any(byte[].class);
		InputStream inStream = PowerMockito.mock(InputStream.class);
		PowerMockito.when(inStream.read(buff, anyInt(), anyInt())).thenReturn(1).thenReturn(0);
		
		
		ByteArrayOutputStream swapStream = PowerMockito.mock(ByteArrayOutputStream.class);
		PowerMockito.whenNew(ByteArrayOutputStream.class).withNoArguments().thenReturn(swapStream);
		byte[] in2b = {1,2};
		PowerMockito.when(swapStream.toByteArray()).thenReturn(in2b);
		 
		byte[] result = CommonUtil.input2byte(inStream);
		boolean flag = result[0]==1;
		assertTrue(flag);
		
	}
	
	@Test
	public void testgetIpAddr() throws Exception{
		HttpServletRequest request = PowerMockito.mock(HttpServletRequest.class);
		PowerMockito.when(request.getHeader("x-forwarded-for")).thenReturn(null);
		PowerMockito.when(request.getHeader("Proxy-Client-IP")).thenReturn(null);
		PowerMockito.when(request.getHeader("WL-Proxy-Client-IP")).thenReturn(null);
		PowerMockito.when(request.getHeader("HTTP_CLIENT_IP")).thenReturn(null);
		PowerMockito.when(request.getHeader("HTTP_X_FORWARDED_FOR")).thenReturn(null);
		PowerMockito.when(request.getRemoteAddr()).thenReturn("127.0.0.1");
		PowerMockito.mockStatic(InetAddress.class);
		InetAddress inet = PowerMockito.mock(InetAddress.class);
		PowerMockito.when(InetAddress.getLocalHost()).thenReturn(inet);
		PowerMockito.when(inet.getHostAddress()).thenReturn("127.0.0.1,128.0.0.1");
		assertEquals(CommonUtil.getIpAddr(request), "127.0.0.1");
		//异常测试
		PowerMockito.when(InetAddress.getLocalHost()).thenThrow(new UnknownHostException());
		String result = CommonUtil.getIpAddr(request);
		assertNull(result);
		System.out.println("result="+result);
		
	}
	
	@Test
	public void testException2String() throws Exception{
		Throwable tw = PowerMockito.mock(Throwable.class);
		StringWriter sw = PowerMockito.mock(StringWriter.class);
		PowerMockito.whenNew(StringWriter.class).withAnyArguments().thenReturn(sw);
		PrintWriter pw = PowerMockito.mock(PrintWriter.class);
		PowerMockito.whenNew(PrintWriter.class).withAnyArguments().thenReturn(pw);
		PowerMockito.doNothing().when(tw).printStackTrace(pw);
		PowerMockito.doNothing().when(pw).flush();
		PowerMockito.doNothing().when(sw).flush();
		PowerMockito.when(sw.toString()).thenReturn("aaa");
		assertEquals(CommonUtil.exception2String(tw), "aaa");
	}
		
	@Test
	public void testFormMysqlDate() throws Exception{
//		PowerMockito.spy(CommonUtil.class);
//		SimpleDateFormat sdf = PowerMockito.mock(SimpleDateFormat.class);
//		PowerMockito.whenNew(SimpleDateFormat.class).withAnyArguments().thenReturn(sdf);
//		PowerMockito.when(sdf.format(Mockito.any(Date.class))).thenReturn("1997-06-08");
//		assertEquals(CommonUtil.formMysqlDate(new Date()),"1997-06-08");
		
		String dt = "2018/8/21 12:00:00";
		SimpleDateFormat dFormat = new SimpleDateFormat("y/M/d H:m:s");
		System.out.println(dFormat.parse(dt));
	}
	
	@Test
	public void testByteToHex() throws Exception{
		String result = Whitebox.invokeMethod(CommonUtil.class, "byteToHex", new byte[]{'a'});
		assertEquals(result, "61");				
	}
	
	
}

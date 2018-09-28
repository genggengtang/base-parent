package org.zxs.test.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.zxs.utils.CommonUtil;
import org.zxs.utils.UploadQiniu;

import com.qiniu.common.Zone;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringUtils;
import com.qiniu.util.UrlSafeBase64;

@RunWith(PowerMockRunner.class)
@PrepareForTest({StringUtils.class, OperationManager.class, String.class, Auth.class,URLConnection.class, UrlSafeBase64.class, URL.class,UploadQiniu.class, UploadManager.class, Response.class, Zone.class, Auth.class, Client.class, Configuration.class, InputStream.class, CommonUtil.class})
public class QiniuUploadTest{
    private final String BUCKET_NAME = "origin";
    private final String HTTP_BUCKET_NAME = "jiuyu";
    private final String HTTP_QINIU_HOME_URL="http://cdn.jyblue.com/";
    private final String QINIU_HOME_URL="//qiniu.uulead.com/";
    private String filePath = "F:\\ddd";
	private String fileName = "test.jpg";
    
    @Test 
    public void testGetUpToken(){
    	Auth auth = PowerMockito.mock(Auth.class);
    	PowerMockito.mockStatic(Auth.class);
    	PowerMockito.when(Auth.create(anyString(), anyString())).thenReturn(auth);
    	PowerMockito.when(auth.uploadToken(BUCKET_NAME)).thenReturn("origin");
    	PowerMockito.when(auth.uploadToken(HTTP_BUCKET_NAME)).thenReturn("jiuyu");
    	boolean flag = true;
    	String result = UploadQiniu.getUpToken(flag);
    	assertEquals("jiuyu", result);
    	
    	flag = false;
    	result = UploadQiniu.getUpToken(flag);
    	assertEquals("origin", result);
    	
    	result = UploadQiniu.getUpToken();
    	assertEquals("origin", result);
    }
    
    @Test
    public void testUpload() throws Exception{
    	Zone z = PowerMockito.mock(Zone.class);
    	PowerMockito.mockStatic(Zone.class);
    	PowerMockito.when(Zone.autoZone()).thenReturn(z);
    	Configuration c = PowerMockito.mock(Configuration.class);
    	PowerMockito.whenNew(Configuration.class).withArguments(z).thenReturn(c);
    	UploadManager uploadManager = PowerMockito.mock(UploadManager.class);
    	PowerMockito.whenNew(UploadManager.class).withArguments(c).thenReturn(uploadManager);
    	Auth auth = PowerMockito.mock(Auth.class);
    	PowerMockito.mockStatic(Auth.class);
    	PowerMockito.when(Auth.create(anyString(), anyString())).thenReturn(auth);
    	PowerMockito.when(auth.uploadToken(anyString())).thenReturn("bucketName");
    	Response res = PowerMockito.mock(Response.class);
    	PowerMockito.when(uploadManager.put(filePath, fileName, "bucketName")).thenReturn(res);
    	PowerMockito.when(res.bodyString()).thenReturn("{\"key\":\"test.jpg\"}");
    	
    	String result = UploadQiniu.upload(filePath, fileName, true);
    	assertEquals(HTTP_QINIU_HOME_URL+"test.jpg", result);
    	
    	result = UploadQiniu.upload(filePath, fileName, false);
    	assertEquals(QINIU_HOME_URL+"test.jpg", result);
    	 
    }
    

//    @Test
//	public void testUploadWithUploadManager() throws Exception {
//		UploadManager uploadManager = PowerMockito.mock(UploadManager.class);
//		
//		Zone z = PowerMockito.mock(Zone.class);
//		PowerMockito.mockStatic(Zone.class);
//		PowerMockito.when(Zone.autoZone()).thenReturn(z);
//		
//		PowerMockito.mockStatic(Auth.class);
//		Auth auth = PowerMockito.mock(Auth.class);
//		PowerMockito.when(Auth.create(Mockito.anyString(), Mockito.anyString())).thenReturn(auth);
//		
//		String policy = BUCKET_NAME;
//		PowerMockito.when(auth.uploadToken(BUCKET_NAME)).thenReturn(policy);
//		
//		Response res = PowerMockito.mock(Response.class);
//		PowerMockito.when(res.bodyString()).thenReturn("{'key':'123456.jpg'}");
//		PowerMockito.when(uploadManager.put("", "", policy)).thenReturn(res);
//		
//		String url = UploadQiniu.upload("", "", uploadManager, policy, "//qiniu.uulead.com/");
//		assertEquals("//qiniu.uulead.com/123456.jpg", url);
//	}
	
	@Test
	public void testUploadWithString() throws Exception {
		UploadQiniu upload = new UploadQiniu();
		assertNotNull(upload);
		
		UploadManager uploadManager = PowerMockito.mock(UploadManager.class);
		PowerMockito.whenNew(UploadManager.class).withAnyArguments().thenReturn(uploadManager);
		
		Zone z = PowerMockito.mock(Zone.class);
		PowerMockito.mockStatic(Zone.class);
		PowerMockito.when(Zone.autoZone()).thenReturn(z);
		
//		Client client = PowerMockito.mock(Client.class);
//		PowerMockito.whenNew(Client.class).withAnyArguments().thenReturn(client);
		
		PowerMockito.mockStatic(Auth.class);
		Auth auth = PowerMockito.mock(Auth.class);
		PowerMockito.when(Auth.create(Mockito.anyString(), Mockito.anyString())).thenReturn(auth);
		String policy = BUCKET_NAME;
		PowerMockito.when(auth.uploadToken(BUCKET_NAME)).thenReturn(policy);
		Response res = PowerMockito.mock(Response.class);
		PowerMockito.when(res.bodyString()).thenReturn("{'key':'123456.jpg'}");
		PowerMockito.when(uploadManager.put("", "", policy)).thenReturn(res);
		
		String url = UploadQiniu.upload("", "");
		assertEquals("//qiniu.uulead.com/123456.jpg", url);
	}
	
	@Test
	public void testUploadWithStream() throws Exception {
		InputStream is = PowerMockito.mock(InputStream.class);
		
		PowerMockito.mockStatic(CommonUtil.class);
		PowerMockito.when(CommonUtil.getHttpStream(Mockito.anyString())).thenReturn(is);
		
		byte[] picDataArray = new byte[0];
		PowerMockito.when(CommonUtil.input2byte(Mockito.any(InputStream.class))).thenReturn(picDataArray);
		
		UploadManager uploadManager = PowerMockito.mock(UploadManager.class);
		PowerMockito.whenNew(UploadManager.class).withAnyArguments().thenReturn(uploadManager);
		
		Client client = PowerMockito.mock(Client.class);
		PowerMockito.whenNew(Client.class).withAnyArguments().thenReturn(client);
		
		Zone z = PowerMockito.mock(Zone.class);
		PowerMockito.mockStatic(Zone.class);
		PowerMockito.when(Zone.autoZone()).thenReturn(z);
		PowerMockito.when(Zone.zone0()).thenReturn(z);
		
		PowerMockito.mockStatic(Auth.class);
		Auth auth = PowerMockito.mock(Auth.class);
		PowerMockito.when(Auth.create(Mockito.anyString(), Mockito.anyString())).thenReturn(auth);
		
		String policy = BUCKET_NAME;
		PowerMockito.when(auth.uploadToken(BUCKET_NAME)).thenReturn(policy);
		
		Response res = PowerMockito.mock(Response.class);
		PowerMockito.when(res.bodyString()).thenReturn("{'key':'123456.jpg'}");
		PowerMockito.when(uploadManager.put(picDataArray, "", policy)).thenReturn(res);
		
		String url = UploadQiniu.uploadFromWeb("", "");
		assertEquals("//qiniu.uulead.com/123456.jpg", url);
		
		InputStream inStream = PowerMockito.mock(InputStream.class);
		PowerMockito.mockStatic(CommonUtil.class);
		PowerMockito.when(CommonUtil.getHttpStream(anyString())).thenReturn(inStream);
		PowerMockito.when(CommonUtil.input2byte(inStream)).thenReturn(picDataArray);
		url = UploadQiniu.uploadImgQiniu(inStream, "");
		assertEquals("//qiniu.uulead.com/123456.jpg", url);
		
		 
		//http
		PowerMockito.when(auth.uploadToken(BUCKET_NAME)).thenReturn("origin");
    	PowerMockito.when(auth.uploadToken(HTTP_BUCKET_NAME)).thenReturn("jiuyu");
		
		PowerMockito.when(uploadManager.put(picDataArray, "", HTTP_BUCKET_NAME)).thenReturn(res);
		
		url = UploadQiniu.uploadFromWeb("", "", true);
		assertEquals(HTTP_QINIU_HOME_URL+"123456.jpg", url);
		
		//视频转码
		PowerMockito.mockStatic( UrlSafeBase64.class);  
		PowerMockito.when(UrlSafeBase64.encodeToString(anyString())).thenReturn("base64");
		PowerMockito.mockStatic( String.class); 
		PowerMockito.when(String.format(anyString(), anyString())).thenReturn("avthumbMp4Fop");
		String[] str = {"avthumbMp4Fop"};
		str = Matchers.any(String[].class);
		PowerMockito.mockStatic(StringUtils.class);
		PowerMockito.when(StringUtils.join(any(String[].class), ";")).thenReturn("persistentOpfs");
		Configuration c = PowerMockito.mock(Configuration.class);
		PowerMockito.whenNew(Configuration.class).withArguments(z).thenReturn(c);
		
		OperationManager operationMgr = PowerMockito.mock(OperationManager.class);
		PowerMockito.whenNew(OperationManager.class).withArguments(auth, c).thenReturn(operationMgr);
		PowerMockito.when(uploadManager.put(picDataArray, "123.mp4", policy)).thenReturn(res);
		url = UploadQiniu.uploadFromWeb("123", "123.mp4");
		assertEquals(QINIU_HOME_URL+"tran_123.mp4", url);
		
		url = UploadQiniu.uploadImgQiniu(inStream, "123.mp4");
		assertEquals("//qiniu.uulead.com/tran_123.mp4", url);
	}
	
	@Test
	public void testVideoTransCodec() throws Exception{
		testPublic();
		String result = UploadQiniu.videoTransCodec(BUCKET_NAME, "persistentNotifyUrl");
		assertEquals("tran_"+BUCKET_NAME, result);
		
		
	}
	
	private static void testPublic() throws Exception{
		PowerMockito.mockStatic( UrlSafeBase64.class);  
		PowerMockito.when(UrlSafeBase64.encodeToString(anyString())).thenReturn("base64");
		PowerMockito.mockStatic(StringUtils.class);
		String[] str = {"avthumbMp4Fop"};
		str = Matchers.any(String[].class);
		PowerMockito.when(StringUtils.join(any(String[].class), ";")).thenReturn("persistentOpfs");
		
		Zone z = PowerMockito.mock(Zone.class);
		PowerMockito.mockStatic(Zone.class);
		PowerMockito.when(Zone.zone0()).thenReturn(z);
		
		Configuration c = PowerMockito.mock(Configuration.class);
		PowerMockito.whenNew(Configuration.class).withArguments(z).thenReturn(c);
		
		PowerMockito.mockStatic(Auth.class);
		Auth auth = PowerMockito.mock(Auth.class);
		PowerMockito.when(Auth.create(anyString(),anyString())).thenReturn(auth);
		
		OperationManager operationMgr = PowerMockito.mock(OperationManager.class);
		PowerMockito.whenNew(OperationManager.class).withArguments(auth, c).thenReturn(operationMgr);
	}
	
	/**
	 * 测试UploadQiniu.inputstreamtofile()方法 
	 */
	@Test
	public void inputstreamtofileTest() throws Exception{
		//异常测试
		String strUrl = "www:123.com";
		InputStream ins = UploadQiniu.inputstreamtofile(strUrl);
		assertNull(ins);
		strUrl = "https://ww.123.com";
		ins = UploadQiniu.inputstreamtofile(strUrl);
		assertNull(ins);
		System.out.println("Test Exception：\"UploadQiniu.inputstreamtofile(String url)\"'s exception test");
		//正常测试 
		URL url = PowerMockito.mock(URL.class);  //url=:?#
		PowerMockito.whenNew(URL.class).withAnyArguments().thenReturn(url);
		
		URLConnection con = PowerMockito.mock(URLConnection.class);
		PowerMockito.when(url.openConnection()).thenReturn(con);
		
		InputStream in = PowerMockito.mock(InputStream.class);           //inputStream
		PowerMockito.when(con.getInputStream()).thenReturn(in);
		
		ins = UploadQiniu.inputstreamtofile("");
		assertEquals(in, ins);  
		System.out.println("Test：UploadQiniu.inputstreamtofile(String url)："+ ins);
	}
	
	@Test
	public void getQiniuHomeUrlTest(){
		String result = UploadQiniu.getQiniuHomeUrl();
		assertEquals(result, "//qiniu.uulead.com/");
		System.out.println("Test：UploadQiniu.getQiniuHomeUrl()："+result);
		
		result = UploadQiniu.getHttpQiniuHomeUrl();
		assertEquals(result, "http://cdn.jyblue.com/");
		
	}
	
}

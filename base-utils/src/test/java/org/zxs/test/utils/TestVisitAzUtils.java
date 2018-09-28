package org.zxs.test.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.zxs.utils.HttpUtils;
import org.zxs.utils.VisitAzUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpUtils.class, HttpResponse.class, HttpEntity.class, EntityUtils.class,  })
public class TestVisitAzUtils {
	private static final String HOST = "host";
	private static final String SESSION= "session";
	private static final String URL = "url";
	private static final String TABLENAME = "tableName";
	private static final String [] COLUMNNAMES = new String []{"columnName"};
	
	private HttpResponse httpResponse = PowerMockito.mock(HttpResponse.class);
	private HttpEntity httpEntity = PowerMockito.mock(HttpEntity.class);
	
	@Test
	public void getAzSessionTest() throws Exception{ 
		VisitAzUtils visit = PowerMockito.mock(VisitAzUtils .class);
		assertNotNull(visit);
		
		PowerMockito.mockStatic(HttpUtils.class);
		PowerMockito.when(HttpUtils.doPost(Mockito.anyString(),Mockito.anyString(), Mockito.anyMap(),Mockito.anyMap(),Mockito.anyString())).thenReturn(httpResponse);
		PowerMockito.when(httpResponse.getEntity()).thenReturn(httpEntity);
		
		String resourceId ="resourceId";
		String user2Dev = "user2Dev";
		String pwd2Dev = "pwd2Dev";
		String result = VisitAzUtils.getAzSession(HOST, resourceId, user2Dev, pwd2Dev);
		assertEquals(result, "");
		System.out.println("测试VisitAzUtils.getAzSession() if(false):"+result);
		
		String jsonString ="{\"session\":\"v1\"}";
		EntityUtils entityUtils = PowerMockito.mock(EntityUtils.class);
		PowerMockito.mockStatic(EntityUtils.class);
		PowerMockito.when(EntityUtils.toString(httpEntity, "utf-8")).thenReturn(jsonString);
		result = VisitAzUtils.getAzSession(HOST, resourceId, user2Dev, pwd2Dev);
		assertEquals(result, "v1");
		System.out.println("测试VisitAzUtils.getAzSession() if(true):"+result);
	}
	
	@Test
	public void postSearchIntefaceAndPostDefaultTest() throws Exception{
		
		PowerMockito.mockStatic(HttpUtils.class);
		PowerMockito.when(HttpUtils.doPost(Mockito.anyString(), Mockito.anyString(), Mockito.anyMap(), Mockito.anyMap(), Mockito.anyString() )).thenReturn(httpResponse);
		PowerMockito.when(httpResponse.getEntity()).thenReturn(httpEntity);
		String searchRespStr ="searchRespStr";
		PowerMockito.mockStatic(EntityUtils.class);
		PowerMockito.when(EntityUtils.toString(httpEntity, "utf-8")).thenReturn(searchRespStr);
		  
		String [][]conditions = new String [][]{{"conditions"}};
		Map<String,String> pageMap = new HashMap<String,String>();
		pageMap.put("k1", "v1");
		Map<String,String> orderBy = new HashMap<String,String>();
		orderBy.put("k2", "v2");
		
		String result = VisitAzUtils.postSearchInteface(SESSION, HOST, URL, TABLENAME, COLUMNNAMES, conditions, pageMap, orderBy);
		assertEquals(searchRespStr, result);
		System.out.println("测试VisitAzUtils.postSearchInteface():"+result);
		
		result = VisitAzUtils.postDefaultSearchInteface(SESSION, HOST, URL, TABLENAME, COLUMNNAMES);
		assertEquals(searchRespStr, result);
		System.out.println("测试VisitAzUtils.postDefaultSearchInteface():"+result);
		
	}
	
	@Test 
	public void postSearchIntefaceGroupByTest() throws Exception{
		PowerMockito.mockStatic(HttpUtils.class);
		PowerMockito.when(HttpUtils.doPost(Mockito.anyString(), Mockito.anyString(), Mockito.anyMap(), Mockito.anyMap(), Mockito.anyString() )).thenReturn(httpResponse);
		PowerMockito.when(httpResponse.getEntity()).thenReturn(httpEntity);
		String searchRespStr ="searchRespStr";
		PowerMockito.mockStatic(EntityUtils.class);
		PowerMockito.when(EntityUtils.toString(httpEntity, "utf-8")).thenReturn(searchRespStr);
		
		String [][]conditions = new String [][]{{"conditions"}};
		String[] groupBy = new String []{"groupBy"};
		Map<String,String> pageMap = new HashMap<String,String>();
		pageMap.put("k1", "v1");
		Map<String,String> orderBy = new HashMap<String,String>();
		orderBy.put("k2", "v2");
		String result = VisitAzUtils.postSearchIntefaceGroupBy(SESSION, HOST, URL, TABLENAME, COLUMNNAMES, conditions, groupBy, pageMap, orderBy);
		assertEquals(searchRespStr, result);
		System.out.println("测试VisitAzUtils.postSearchIntefaceGroupBy():"+result);
		
	}
	
}

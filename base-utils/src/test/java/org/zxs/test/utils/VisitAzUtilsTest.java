package org.zxs.test.utils;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.zxs.utils.VisitAzUtils;

public class VisitAzUtilsTest {
	
	@Ignore
	@Test
	public void testPostHbSearch() throws Exception {
		String sessionStr = VisitAzUtils.getAzSession("http://10.1.14.209:6061", "007565768-G1002", "应急系统开发", "yjh9g0BSh19K73a4");
		
		if(sessionStr != null && !sessionStr.isEmpty()){
			String interfId = "00756642917060020001-1"; // 接口数据资源ID
			String url = "/developer-api/sync/resources/" + interfId + "/records/query";
			
			String[] columnArr = {"Airqualitycode", "UpdateTime", "AQI", "PM25", "PM10"};
			
			Map<String,String> page = new HashMap<>();
			page.put("offset", "0");
			page.put("limit", "5");
			
			Map<String,String> orderBy = new HashMap<>();
			orderBy.put("column", "UpdateTime");
			orderBy.put("order", "desc");
			
			String searchRet = VisitAzUtils.postSearchInteface(sessionStr, "http://10.1.14.209:6062", url, 
					"dbo.YJ_AIRQUALITY_INFO", columnArr, null, page, orderBy);
			System.out.println(searchRet);
		}
	}
	
	@Ignore
	@Test
	public void testPostHbWebSearch() throws Exception {
		String sessionStr = VisitAzUtils.getAzSession("http://221.7.197.124:6034", "007565768-G1002", "应急系统开发", "yjh9g0BSh19K73a4");
		
		if(sessionStr != null && !sessionStr.isEmpty()){
			String interfId = "00756642917060020001-1"; // 接口数据资源ID
			String url = "/developer-api/sync/resources/" + interfId + "/records/query";
			
			String[] columnArr = {"Airqualitycode", "UpdateTime", "AQI", "PM25", "PM10"};
			
			Map<String,String> page = new HashMap<>();
			page.put("offset", "0");
			page.put("limit", "5");
			
			Map<String,String> orderBy = new HashMap<>();
			orderBy.put("column", "UpdateTime");
			orderBy.put("order", "desc");
			
			String searchRet = VisitAzUtils.postSearchInteface(sessionStr, "http://221.7.197.124:6062", url, 
					"dbo.YJ_AIRQUALITY_INFO", columnArr, null, page, orderBy);
			System.out.println(searchRet);
		}
	}

}

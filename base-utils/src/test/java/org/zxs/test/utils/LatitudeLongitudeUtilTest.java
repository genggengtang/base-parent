package org.zxs.test.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.reporter.PowerMockReporter;
import org.powermock.modules.junit4.PowerMockRunner;
import org.zxs.utils.LatitudeLongitudeUtil;
@RunWith(PowerMockRunner.class)
@PrepareForTest({File.class, LatitudeLongitudeUtil.class, FileInputStream.class, InputStreamReader.class, BufferedReader.class })
public class LatitudeLongitudeUtilTest {
	private String filePath = "dsdd";
	private LatitudeLongitudeUtil llu = new LatitudeLongitudeUtil();
	
	@Test 
	public void testLatLonApproximate() throws Exception{
		LatitudeLongitudeUtil llu = PowerMockito.mock(LatitudeLongitudeUtil.class);
		assertNotNull(llu);
		File file = PowerMockito.mock(File.class);
		PowerMockito.whenNew(File.class).withAnyArguments().thenReturn(file);
		 //文件存在
		PowerMockito.when(file.exists()).thenReturn(true);
		FileInputStream fis = PowerMockito.mock(FileInputStream.class);
		PowerMockito.whenNew(FileInputStream.class).withArguments(file).thenReturn(fis);
		InputStreamReader ips = PowerMockito.mock(InputStreamReader.class);
		PowerMockito.whenNew(InputStreamReader.class).withArguments(fis, "GBK").thenReturn(ips);
		BufferedReader br = PowerMockito.mock(BufferedReader.class);
		PowerMockito.whenNew(BufferedReader.class).withArguments(ips).thenReturn(br);
		String wangGe = "1.875 -1.875 0 358.125 90 -10 192 49 ";
		PowerMockito.when(br.readLine()).thenReturn("1").thenReturn("2").thenReturn("3").thenReturn("4").thenReturn(wangGe).thenReturn(null);
		
		// 整格  1.875                 
		double latitude = 86.25;   //纬度  90 - -10 
		double longitude = 86.25;
		PowerMockito.when(br.readLine()).thenReturn("1").thenReturn("2").thenReturn("3").thenReturn("4").thenReturn(wangGe).thenReturn(null);
		Map<String, Double> latLonMap = LatitudeLongitudeUtil.latLonApproximate(filePath, latitude, longitude);
		latitude = latLonMap.get("latitude");
		assertTrue(86.25==latitude);
		longitude = latLonMap.get("longitude");
		assertTrue(86.25==longitude);
		//-纬度<0
		latitude = -5.123;
		longitude = 5.123;
		PowerMockito.when(br.readLine()).thenReturn("1").thenReturn("2").thenReturn("3").thenReturn("4").thenReturn(wangGe).thenReturn(null);
		latLonMap = LatitudeLongitudeUtil.latLonApproximate(filePath, latitude, longitude);
		latitude = latLonMap.get("latitude");
		assertTrue(-5.625==latitude);
		longitude = latLonMap.get("longitude");
		assertTrue(5.625==longitude);
		
		// 半格
		latitude = 87.1875;
		longitude = 87.1875;
		PowerMockito.when(br.readLine()).thenReturn("1").thenReturn("2").thenReturn("3").thenReturn("4").thenReturn(wangGe).thenReturn(null);
		latLonMap = LatitudeLongitudeUtil.latLonApproximate(filePath, latitude, longitude);
		latitude = latLonMap.get("latitude");
		assertTrue(87.1875==latitude);
		longitude = latLonMap.get("longitude");
		assertTrue(87.1875==longitude);
		
		//不足半格
		PowerMockito.when(br.readLine()).thenReturn("1").thenReturn("2").thenReturn("3").thenReturn("4").thenReturn(wangGe).thenReturn(null);
		latitude = 87.1625;
		longitude = 87.1625;
		latLonMap = LatitudeLongitudeUtil.latLonApproximate(filePath, latitude, longitude);
		latitude = latLonMap.get("latitude");
		assertTrue(86.25==latitude);
		longitude = latLonMap.get("longitude");
		assertTrue(86.25==longitude);
	
		//大于半格
		PowerMockito.when(br.readLine()).thenReturn("1").thenReturn("2").thenReturn("3").thenReturn("4").thenReturn(wangGe).thenReturn(null);
		latitude = 87.2625;
		longitude = 87.2625;
		latLonMap = LatitudeLongitudeUtil.latLonApproximate(filePath, latitude, longitude);
		latitude = latLonMap.get("latitude");
		assertTrue(88.125==latitude);
		longitude = latLonMap.get("longitude");
		assertTrue(88.125==longitude);
		
		//超范围
		latitude = -11;
		longitude = -1;
		PowerMockito.when(br.readLine()).thenReturn("1").thenReturn("2").thenReturn("3").thenReturn("4").thenReturn(wangGe).thenReturn(null);
		latLonMap = LatitudeLongitudeUtil.latLonApproximate(filePath, latitude, longitude);
		latitude = latLonMap.get("latitude");
		assertTrue(0.0==latitude);
		longitude = latLonMap.get("longitude");
		assertTrue(0.0==longitude);
		
		latitude = 91;
		longitude = 358.2;
		PowerMockito.when(br.readLine()).thenReturn("1").thenReturn("2").thenReturn("3").thenReturn("4").thenReturn(wangGe).thenReturn(null);
		latLonMap = LatitudeLongitudeUtil.latLonApproximate(filePath, latitude, longitude);
		latitude = latLonMap.get("latitude");
		assertTrue(0.0==latitude);
		longitude = latLonMap.get("longitude");
		assertTrue(0.0==longitude);
		
	}
	
	
	@Test 
	public void testReadFileByLines() throws Exception{
		
		File file = PowerMockito.mock(File.class);
		PowerMockito.whenNew(File.class).withAnyArguments().thenReturn(file);
		//文件不存在
		PowerMockito.when(file.exists()).thenReturn(false);
		String fileName = "G:\\job\\latLon\\123.txt";
		Map<String, Double> reMap = llu.readFileByLines(fileName);
		boolean flag = reMap.isEmpty();
		assertTrue(flag);
		 
		 //文件存在
		PowerMockito.when(file.exists()).thenReturn(true);
		FileInputStream fis = PowerMockito.mock(FileInputStream.class);
		PowerMockito.whenNew(FileInputStream.class).withArguments(file).thenReturn(fis);
		InputStreamReader ips = PowerMockito.mock(InputStreamReader.class);
		PowerMockito.whenNew(InputStreamReader.class).withArguments(fis, "GBK").thenReturn(ips);
		BufferedReader br = PowerMockito.mock(BufferedReader.class);
		PowerMockito.whenNew(BufferedReader.class).withArguments(ips).thenReturn(br);
		String wangGe = "0.125000 -0.125000 60.000000 150.000000 60.000000 -10.000000 721 561";
		PowerMockito.when(br.readLine()).thenReturn("1").thenReturn("2").thenReturn("3").thenReturn("4").thenReturn(wangGe).thenReturn(null);
		reMap = llu.readFileByLines(fileName);
		flag = reMap.get("longitudeInterval")==0.125 && reMap.get("startLongitude")==60.0 && reMap.get("endLongitude")==150.0 ;
		assertTrue(flag);
		 
		 //wangGe=null;
		wangGe = null;
		PowerMockito.when(br.readLine()).thenReturn("1").thenReturn("2").thenReturn("3").thenReturn("4").thenReturn(wangGe).thenReturn(null);
		reMap = llu.readFileByLines(fileName);
		flag = reMap.get("longitudeInterval")==0.0 && reMap.get("startLongitude")==0.0 && reMap.get("endLongitude")==0.0 ;
		assertTrue(flag);
		
		//抛异常
		wangGe = "0.125000t -0.125000 60.000000 150.000000 60.000000 -10.000000 721 561";
		PowerMockito.when(br.readLine()).thenReturn("1").thenReturn("2").thenReturn("3").thenReturn("4").thenReturn(wangGe).thenReturn(null);
		try {
			reMap = llu.readFileByLines(fileName);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		assertFalse(flag);
		
	}
	
}

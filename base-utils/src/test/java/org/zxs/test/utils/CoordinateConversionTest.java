//package org.zxs.test.utils;
//
//import static org.junit.Assert.assertTrue;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.zxs.base.model.Point;
//import org.zxs.utils.CoordinateConversion;
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(CoordinateConversion.class)
//public class CoordinateConversionTest {
//
//	@Test
//	public void testGauss2Bd(){
//		Point point = new Point();
//		point = CoordinateConversion.gauss2Bd(110.23, 23.46);
//		assertTrue(point.getLng()==-7.4802430396429695);
//	}
//	
//	@Test
//	public void testGoogleBdEncrypt(){
//		Point point = new Point();
//		point = CoordinateConversion.google_bd_encrypt(110.23, 23.46);
//		assertTrue(point.getLng()==23.466828608210665);
//	}
//	
//	@Test
//	public void testBdGoogleEncrypt(){
//		Point point = new Point();
//		point = CoordinateConversion.bd_google_encrypt(110.23, 23.46);
//		assertTrue(point.getLng()==23.453189160163774);
//	}
//	
//	@Test
//	public void testWgsGcjEncrypts(){
//		Point point = new Point();
//		point = CoordinateConversion.wgs_gcj_encrypts(23.23, 110.36);
//		assertTrue(point.getLng()==110.3646091699085);
//	}
//	
//	@Test
//	public void testTransform(){
//		try {
//			CoordinateConversion.transform(23.23,110.36,new double[]{1,2});
//			CoordinateConversion.transform(56.56,110.36,new double[]{1,2});
//			assertTrue(true);
//		} catch (Exception e) {
//			e.printStackTrace( );
//		}
//	}
//	
//	@Test
//	public void testGaussToBLToGauss(){
//		double[] output = CoordinateConversion.gaussToBLToGauss(23.25, 110.23);
//		assertTrue(output[0]==4413151.106568887);
//	}
//}

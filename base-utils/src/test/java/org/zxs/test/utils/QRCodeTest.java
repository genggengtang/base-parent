//package org.zxs.test.utils;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Matchers.anyInt;
//
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import javax.imageio.ImageIO;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Matchers;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.zxs.utils.QRCodeUtil;
//
//import com.swetake.util.Qrcode;
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({byte[].class,Graphics2D.class,ImageIO.class, ByteArrayOutputStream.class, QRCodeUtil.class, File.class, Image.class})
//public class QRCodeTest {
//	@Test
//	public void testEncoderQRCode() throws Exception{
//		//内容为空
//		String result = QRCodeUtil.encoderQRCode("", "");
//		assertEquals("", result);
//		
//		//不为空
//		ByteArrayOutputStream imgOut = PowerMockito.mock(ByteArrayOutputStream.class);
//		PowerMockito.whenNew(ByteArrayOutputStream.class).withAnyArguments().thenReturn(imgOut);
//
//		//超限制 
//		String content = "1的法国队放大非福建广的法国队放大非福建广的法国队放大非福建广的法国队放大非福建广的法国队放大非福建广的法国队放大非福建广";
//		result = QRCodeUtil.encoderQRCode(content, "");
//		assertEquals("", result);
//		//不超
//		byte[] imgByte = {1};
//		PowerMockito.when(imgOut.toByteArray()).thenReturn(imgByte);
//		content = "123";
//		result = QRCodeUtil.encoderQRCode(content, "");
//		assertEquals("AQ==", result);
//		
//		//=最大值,logoFile不存在
//		File logoFile = PowerMockito.mock(File.class);
//		PowerMockito.whenNew(File.class).withAnyArguments().thenReturn(logoFile);
//		PowerMockito.when(logoFile.exists()).thenReturn(false);
//		content = "的法国队放大非福建广的法国队放大非福建广的法国队放大非福建广的法国队放大非福建广的法国队放大非福建广的法国队放大非福建广";
//		result = QRCodeUtil.encoderQRCode(content, "ddd");
//		assertEquals("AQ==", result);
//		
//		//logoFile存在,但不是图片格式
//		PowerMockito.when(logoFile.exists()).thenReturn(true);
//		Image image = PowerMockito.mock(Image.class);
//		PowerMockito.mockStatic(ImageIO.class);
//		PowerMockito.when(ImageIO.read(logoFile)).thenReturn(null);
//		result = QRCodeUtil.encoderQRCode(content, "ddd");
//		assertEquals("AQ==", result);
//		
//		//是图片格式，尺寸超48
//		 boolean[][] codeOut = {{true,false}};
//		Qrcode qc = PowerMockito.mock(Qrcode.class);
//		PowerMockito.whenNew(Qrcode.class).withNoArguments().thenReturn(qc);
//		imgByte = Matchers.any(byte[].class);
//		PowerMockito.when(qc.calQrcode(imgByte)).thenReturn(codeOut);
//		 
//		BufferedImage bi = PowerMockito.mock(BufferedImage.class);
//		PowerMockito.whenNew(BufferedImage.class).withArguments(anyInt(), anyInt(), anyInt()).thenReturn(bi);
//		Graphics2D g = PowerMockito.mock(Graphics2D.class);
//		PowerMockito.when(bi.createGraphics()).thenReturn(g);
//	
//		PowerMockito.when(ImageIO.read(logoFile)).thenReturn(bi);
//		PowerMockito.when(bi.getWidth(null)).thenReturn(50);
//		PowerMockito.when(bi.getHeight(null)).thenReturn(45);
//		PowerMockito.when(bi.getScaledInstance(anyInt(), anyInt(), anyInt())).thenReturn(image);
//		PowerMockito.when(g.drawImage(image, 0, 0, null)).thenReturn(true);
//		result = QRCodeUtil.encoderQRCode(content, "ddd");
//		assertEquals("AQ==", result);
//		
//		PowerMockito.when(bi.getWidth(null)).thenReturn(45);
//		PowerMockito.when(bi.getHeight(null)).thenReturn(50);
//		result = QRCodeUtil.encoderQRCode(content, "ddd");
//		assertEquals("AQ==", result);
//		//不超
//		PowerMockito.when(bi.getWidth(null)).thenReturn(45);
//		PowerMockito.when(bi.getHeight(null)).thenReturn(45);
//		result = QRCodeUtil.encoderQRCode(content, "ddd");
//		assertEquals("AQ==", result);
//	}
//}

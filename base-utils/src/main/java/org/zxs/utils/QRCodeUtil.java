//package org.zxs.utils;
//
//
//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//
//import javax.imageio.ImageIO;
//
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.swetake.util.Qrcode;
//
//public class QRCodeUtil {
//	
//	private final static Log log = LogFactory.getLog(QRCodeUtil.class);
//	
//	 // LOGO宽度
//    private final static int LOGO_WIDTH = 48;
//    // LOGO高度
//    private final static int LOGO_HEIGHT = 48;
//    
//	//二维码版本（1-40）
//	private final static int QRCODE_VERSION = 9;
//	 //最大存储容量
//	private final static int MAX_CONTENT_BYTES = 180;
//	 //最小存储容量
//	private final static int MIN_CONTENT_BYTES = 0;
//	 
//	private final static String FORMATNAME = "png";
//	 
//	 
//	/**
//	 * 生成二维码图片
//	 * @param content 内容
//	 * @param logoPath logo图片路径
//	 */
//	public static String encoderQRCode(String content,  String logoPath) throws Exception {  
//	    	
//		    String imgString = "";
//	    	if(StringUtils.isBlank(content)){
//	    		log.info("QRCodeUtil.encoderQRCode生成二维码失败，原因是内容content为空");
//	    		return imgString; 
//	    	}
//	    	
//	    	BufferedImage bufImg = qRCodeCommon(content, logoPath);  //用于生成二维码图片
//	    	if(null==bufImg){
//	    		log.info("QRCodeUtil.qRCodeCommon 生成二维码失败，原因是：存储的内容超过限制字节范围(0,180]！内容content：\""+content+"\"");
//	    	}else{
//	    		//==转为流
//	    		ByteArrayOutputStream imgOut = new ByteArrayOutputStream();
//	    		ImageIO.write(bufImg, FORMATNAME, imgOut); 
//	    		byte[] imgByte = imgOut.toByteArray(); 
//	    		imgString = Base64.encodeBase64String(imgByte); //base64格式
//	    //		imgString = CommonUtil.byteToHex(imgByte).toUpperCase();  //16进制格式
//	    	}
//	    	return imgString; 
//	    } 
//	
//
//	    /** 
//	     * 生成二维码(QRCode) 
//	     * @param content 存储内容 
//	     * @param logoPath logo图片路径
//	     * @return 
//	     */  
//	    private static BufferedImage qRCodeCommon(String content, String logoPath ) throws Exception {  
//	        BufferedImage bufImg = null;  
//	        byte[] contentBytes = content.getBytes("UTF-8"); 
//	        if( (contentBytes.length>MIN_CONTENT_BYTES && contentBytes.length<MAX_CONTENT_BYTES) || contentBytes.length==MAX_CONTENT_BYTES ){
//		         Qrcode qrcodeHandler = new Qrcode();  
//		         qrcodeHandler.setQrcodeErrorCorrect('M');  // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小 
//		         qrcodeHandler.setQrcodeEncodeMode('B');    //N代表数字，A代表a-Z，B代表其它字符 
//		         qrcodeHandler.setQrcodeVersion(QRCODE_VERSION); // 设置设置二维码版本，取值范围1-40，
//		         
//		         int imgSize = 69 + 12 * (QRCODE_VERSION - 1);  
//		         bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);  
//		         Graphics2D gs = bufImg.createGraphics();  
//		            // 设置背景颜色  
//		         gs.setBackground(Color.WHITE);  
//		            // 设定图像颜色> BLACK  
//		         gs.setColor(Color.BLACK);  
//		         gs.clearRect(0, 0, imgSize, imgSize);  
//		  
//		         int pixoff = 3; 
//		         boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes); 
//		         for (int i = 0; i < codeOut.length; i++) {  
//		        		for (int j = 0; j < codeOut.length; j++) {  
//		        			   if (codeOut[j][i]) {  
//		        				   gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
//		        			   }  
//		        		 }  
//		         }  
//		         
//		       //加logo图片
//		    	 if(StringUtils.isNotBlank(logoPath)){
//		    		 File logoFile = new File(logoPath);
//		    		 boolean isExists = logoFile.exists();
//		    		 if(isExists){
//		    			 Image image = ImageIO.read(logoFile);
//		    			 if(!(null==image)){  //是图片logo
//		    				 int imageWidth = image.getWidth(null);
//		    				 int imageHeight = image.getHeight(null);
//		    				 if(imageWidth>LOGO_WIDTH || imageHeight>LOGO_HEIGHT ){
//		    				 	 imageWidth = LOGO_WIDTH;
//		    					 imageHeight = LOGO_HEIGHT;
//		    					 image = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT); //Image img 
//		    					 BufferedImage tag = new BufferedImage(imageWidth, imageHeight,BufferedImage.TYPE_INT_RGB);
//		    					 Graphics2D g = tag.createGraphics();
//		    			         g.drawImage(image, 0, 0, null);// img绘制缩小后的图
//		    			         g.dispose();
//		    				 }
//			            	 gs.drawImage(image, (imgSize - imageWidth) / 2, (imgSize - imageHeight) / 2, null);
//		    			 }else{
//		    				 log.info("加logo失败，原因是传入的文件不是图片格式文件。logoPath="+logoPath);
//		    			 }
//		    		 }else{
//		    			 log.info("加logo失败，原因是logo图片不存在。logoPath="+logoPath);
//		    		 }
//		    	 }
//		    	 
//		         gs.dispose(); 
//		         bufImg.flush();  
//	        }
//	        return bufImg;  
//	    }  
//	    
//}

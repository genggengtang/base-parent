//package org.zxs.test.utils;
//
//import static org.junit.Assert.assertTrue;
//
//import java.io.File;
//import java.security.GeneralSecurityException;
//import java.util.Properties;
//
//import javax.activation.DataHandler;
//import javax.activation.FileDataSource;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.zxs.base.model.Mail;
//import org.zxs.utils.SendMail;
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({FileDataSource.class,File.class,SendMail.class, Mail.class, Properties.class, Session.class, MimeMessage.class, DataHandler.class, MimeMultipart.class, MimeBodyPart.class, Transport.class,InternetAddress.class})
//public class SendMailTest {
//	private static final String MAILSTRING = "mail属性值";
//	private static final String FILENAME = "附件文件的名字";
//	
//	@Test
//	public void testSendMail() throws Exception, MessagingException, GeneralSecurityException, Exception{
//		
//		Mail mail =  PowerMockito.mock(Mail.class);
//		PowerMockito.when(mail.getProtocol() ).thenReturn(MAILSTRING);
//		PowerMockito.when(mail.getMailHost() ).thenReturn(MAILSTRING);
//		PowerMockito.when(mail.getPort() ).thenReturn(MAILSTRING);  //25
//		PowerMockito.when(mail.isSSL() ).thenReturn(true); 
//		PowerMockito.when(mail.getFromMail() ).thenReturn(MAILSTRING);
//		PowerMockito.when(mail.getToMail() ).thenReturn(new String[]{MAILSTRING});
//		PowerMockito.when(mail.getMailTitle() ).thenReturn(MAILSTRING);
//		PowerMockito.when(mail.getMailContent() ).thenReturn(MAILSTRING);
//		PowerMockito.when(mail.getFilePath() ).thenReturn(new String[]{MAILSTRING});
//		PowerMockito.when(mail.getUser() ).thenReturn(MAILSTRING);
//		PowerMockito.when(mail.getPassword() ).thenReturn(MAILSTRING);
//		
//		Properties props = PowerMockito.mock(Properties.class);
//		PowerMockito.whenNew(Properties.class).withAnyArguments().thenReturn(props);
//		
//		Session session = PowerMockito.mock(Session.class);
//		PowerMockito.mockStatic(Session.class);
//		PowerMockito.when(Session.getDefaultInstance(props)).thenReturn(session);
//		
//		MimeMessage message = PowerMockito.mock(MimeMessage.class);
//		PowerMockito.whenNew(MimeMessage.class).withAnyArguments().thenReturn(message);
//		
//		InternetAddress inter = PowerMockito.mock(InternetAddress.class);
//		PowerMockito.whenNew(InternetAddress.class).withAnyArguments().thenReturn(inter);
//		
//		MimeMultipart mul = PowerMockito.mock(MimeMultipart.class);
//		PowerMockito.whenNew(MimeMultipart.class).withAnyArguments().thenReturn(mul);
//		 
//		MimeBodyPart bod = PowerMockito.mock(MimeBodyPart.class);
//		PowerMockito.whenNew(MimeBodyPart.class).withAnyArguments().thenReturn(bod);
//		
//		FileDataSource fileData = PowerMockito.mock(FileDataSource.class);
//		PowerMockito.whenNew(FileDataSource.class).withAnyArguments().thenReturn(fileData);
//		File file = PowerMockito.mock(File.class);
//		PowerMockito.when(fileData.getFile()).thenReturn(file);
//		PowerMockito.when(file.getName()).thenReturn(FILENAME);
//		 
//		DataHandler dataHandler = PowerMockito.mock(DataHandler.class);
//		PowerMockito.whenNew(DataHandler.class).withAnyArguments().thenReturn(dataHandler);
//		
//		Transport transport = PowerMockito.mock(Transport.class);
//		PowerMockito.when(session.getTransport()).thenReturn(transport);
//		try {
//			SendMail.sendMail(mail);
//			assertTrue(true);
//		} catch (Exception e) {
//			e.printStackTrace( );
//		}
//		System.out.println("测试SendMail.sendMail()");
//		 
//	}
//}

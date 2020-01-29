package common.util;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

//import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;//internet.MimeUtility;

import common.bean.MwareConfig;
import common.bean.User;
import common.exception.SqlIgnoreException;
import dream.mgr.message.service.MgrMessageTransDetailService;

public class MailUtil
{ 
    public static String MAIL_ACCOUNT = ""; 
    public static String MAIL_PASSWORD = ""; 
    
    /**
     * ���������� ���۽� ����Ѵ�.
     * @author  Administrator
     * @version $Id: MwareMail.java,v 1.3 2015/10/14 00:18:29 wany Exp $
     * @since   1.0
     * 
     * @param receiver
     * @param sender
     * @param content
     * @param title
     * @return
     * @throws Exception 
     */
    public static int sendMail(String[] receiver, String sender, String senderPwd, String content, String title, User user) throws Exception
    {
        return setMail(receiver, sender, senderPwd, content, title, user);       
    }
    
    /**
     * ���������� ���۽� ����Ѵ�.(�α׾ƿ�����)
     * @author  Administrator
     * @version $Id: MwareMail.java,v 1.3 2015/10/14 00:18:29 wany Exp $
     * @since   1.0
     * 
     * @param receiver
     * @param sender
     * @param content
     * @param title
     * @return
     * @throws Exception 
     */
    public static int sendMail(String[] receiver, String sender, String senderPwd, String content, String title, String compNo) throws Exception
    {
        return setMail(receiver, sender, senderPwd, content, title, compNo, null);       
    }
    
    public static int sendMail(String[] receiver, String sender, String senderPwd, String content, String title, String compNo, List fileList) throws Exception
    {
        return setMail(receiver, sender, senderPwd, content, title, compNo, fileList);       
    }
    
    /**
     * �޴� ����� �Ѹ��� ��� �����Ѵ�.
     * @author  Administrator
     * @version $Id: MwareMail.java,v 1.3 2015/10/14 00:18:29 wany Exp $
     * @since   1.0
     * 
     * @param receiver
     * @param sender
     * @param content
     * @param title
     * @return
     * @throws Exception 
     */
    public static int sendMail(String receiver, String sender, String senderPwd, String content, String title, User user) throws Exception
    {
        String[] receiverArray = new String[1];
        receiverArray[0] = receiver;
        return setMail(receiverArray, sender, senderPwd, content, title, user);
    }
    /**
     * �޴� ����� �Ѹ��� ��� �����Ѵ�.(�α׾ƿ�����)
     * @author  Administrator
     * @version $Id: MwareMail.java,v 1.3 2015/10/14 00:18:29 wany Exp $
     * @since   1.0
     * 
     * @param receiver
     * @param sender
     * @param content
     * @param title
     * @param compNo
     * @return
     */
    public static int sendMail(String receiver, String sender, String senderPwd, String content, String title, String compNo) throws Exception
    {
        String[] receiverArray = new String[1];
        receiverArray[0] = receiver;
        return setMail(receiverArray, sender, senderPwd, content, title, compNo, null);
    }
    
    public static int sendMail(String receiver, String sender, String senderPwd, String content, String title, String compNo, List fileList) throws Exception
    {
        String[] receiverArray = new String[1];
        receiverArray[0] = receiver;
        return setMail(receiverArray, sender, senderPwd, content, title, compNo, fileList);
    }
    
    /**
     * �޼��� Ÿ�Կ� ���� ������ ������.(TAMESSAGELIST�� INSERT)
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param messageObjectType
     * @param objectId
     * @param user
     * @return
     * @throws SqlIgnoreException
     */
    public static String sendMail(String messageObjectType, String objectId, User user) throws SqlIgnoreException
    {
        String rtn = "-1";
        
        try {
            if("Y".equals(MwareConfig.getIsUseMailService())){
                MgrMessageTransDetailService mgrMessageTransDetailService = (MgrMessageTransDetailService) CommonUtil.getBean("mgrMessageTransDetailService", user);
                rtn = mgrMessageTransDetailService.sendMail(messageObjectType, objectId, "MAIL", user);
            }
            else{
                rtn = "2";
            }
        } catch (Exception e) {
            throw new SqlIgnoreException(e.getMessage());
        }
        
        return rtn;
    }

    /**
     * ������ �����Ѵ�.
     * @author  Administrator
     * @version $Id: MwareMail.java,v 1.3 2015/10/14 00:18:29 wany Exp $
     * @since   1.0
     * 
     * @param receiver
     * @param sender
     * @param content
     * @param title
     * @return
     */
    private static int setMail(String[] receiver, String sender, String senderPwd, String content, String title, User user) throws Exception
    {
        int result = 0;
        
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", MwareConfig.getMailSenderServer());
        // MwareConfig.getMailSmtpAuth() false�� �ƴϸ� ��� true
        if("false".equals(MwareConfig.getMailSmtpAuth())){
        	properties.put("mail.smtp.auth", "false");
        }
        else{
        	properties.put("mail.smtp.auth", "true");
        }
        properties.put("mail.smtp.port", MwareConfig.getMailSenderPort()); 
        if("Y".equals(MwareConfig.getMailSenderIsSsl())){
        	properties.put("mail.smtp.ssl.enable", "true"); 
            properties.put("mail.smtp.ssl.trust", MwareConfig.getMailSenderServer());
        }

        MAIL_ACCOUNT =  sender;
        MAIL_PASSWORD =  senderPwd;
        //�н������ ���̵� �Ѱܼ� ���� Ȯ��
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MAIL_ACCOUNT, MAIL_PASSWORD);
            }
        });
        
        // ������ ���� MessageŬ������ �����Ѵ�.
        Message msg = new MimeMessage(session);
        
        // ���� ������� ����
        try 
        {
            int cnt = receiver.length;
            InternetAddress[] receiver_ia = new InternetAddress[cnt];
            for( int i=0 ; i<cnt ; i++ )
            {
                receiver_ia[i] = new InternetAddress(receiver[i]);
            }

            msg.setFrom(new InternetAddress(sender));
            msg.setRecipients(Message.RecipientType.TO, receiver_ia);
            msg.setSubject(MimeUtility.encodeText(title,"UTF-8","B"));
            msg.setSentDate(new Date());

            msg.setContent( content, "text/html;charset=utf-8" );
             
        } 
        catch (AddressException e) 
        {
            e.printStackTrace();
        } 
        catch (MessagingException e) 
        {
            e.printStackTrace();
        }   
        
        // ������ �����Ѵ�.
        try 
        {
            Transport.send(msg);
            result = 1;
        } 
        catch (NoSuchProviderException e1) 
        {
            e1.printStackTrace();
        } 
        catch (MessagingException e) 
        {
            e.printStackTrace();
        }
        
        // 1 : ���� ����, �̿� ����.
        return result;
    }
    
    /**
     * ������ �����Ѵ�.(�α׾ƿ� ����)
     * @author  Administrator
     * @version $Id: MwareMail.java,v 1.3 2015/10/14 00:18:29 wany Exp $
     * @since   1.0
     * 
     * @param receiver
     * @param sender
     * @param content
     * @param title
     * @param compNo
     * @return
     */
    private static int setMail(String[] receiver, String sender, String senderPwd, String content, String title, String compNo, List<Map<String,String>> fileList) throws Exception
    {
        int result = 0;
        
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", MwareConfig.getMailSenderServer());
        // MwareConfig.getMailSmtpAuth() false�� �ƴϸ� ��� true
        if("false".equals(MwareConfig.getMailSmtpAuth())){
        	properties.put("mail.smtp.auth", "false");
        }
        else{
        	properties.put("mail.smtp.auth", "true");
        }
        properties.put("mail.smtp.port", MwareConfig.getMailSenderPort()); 
        if("Y".equals(MwareConfig.getMailSenderIsSsl())){
        	properties.put("mail.smtp.ssl.enable", "true"); 
            properties.put("mail.smtp.ssl.trust", MwareConfig.getMailSenderServer());
        }
        
        MAIL_ACCOUNT = sender;
        MAIL_PASSWORD = senderPwd;
        //�н������ ���̵� �Ѱܼ� ���� Ȯ��
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MAIL_ACCOUNT, MAIL_PASSWORD);
            }
        });
        
        // ������ ���� MessageŬ������ �����Ѵ�.
        Message msg = new MimeMessage(session);
        
        // ���� ������� ����
        try 
        {
            int cnt = receiver.length;
            InternetAddress[] receiver_ia = new InternetAddress[cnt];
            for( int i=0 ; i<cnt ; i++ )
            {
                receiver_ia[i] = new InternetAddress(receiver[i]);
            }
            
            msg.setFrom(new InternetAddress(sender));
            msg.setRecipients(Message.RecipientType.TO, receiver_ia);
            msg.setSubject(MimeUtility.encodeText(title,"UTF-8","B"));
            msg.setSentDate(new Date());
            msg.setHeader("Content-Type", "text/plain;charset=UTF-8");
            
            BodyPart body = new MimeBodyPart();
            body.setContent(content, "text/html;charset=utf-8");
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(body);
            
            File dir= null;

            // null �� ��� for�� ������� �ʵ��� üũ
            if(null != fileList){
	            for(Map<String,String> fileMap : fileList)
	            {
	                dir = new File(fileMap.get("FILEPATH")); 
	                
	                if(dir.exists())
	                {
	                    BodyPart bodyPart = new MimeBodyPart();
	                    DataSource ds = new FileDataSource(dir);
	                    bodyPart.setDataHandler(new DataHandler(ds));
	                    bodyPart.setFileName(fileMap.get("FILENAME"));
	                    multipart.addBodyPart(bodyPart);
	                }
	
	            }
            }
            msg.setContent( multipart, "text/plain;charset=utf-8" );
             
        } 
        catch (AddressException e) 
        {
        	result = -4;
            e.printStackTrace();
        } 
        catch (MessagingException e) 
        {
        	result = -5;
            e.printStackTrace();
        }   
        
        // ������ �����Ѵ�.
        try 
        {
            Transport.send(msg);
            result = 1;
        } 
        catch (NoSuchProviderException e1) 
        {
        	result = -6;
            e1.printStackTrace();
        } 
        catch (MessagingException e) 
        {
        	result = -7;
            e.printStackTrace();
        }
        
        // 1 : ���� ����, �̿� ����.
        return result;
    }
    
    /**
     * ���� ���� ���� ����
     * @author  Administrator
     * @version $Id: MwareMail.java,v 1.3 2015/10/14 00:18:29 wany Exp $
     * @since   1.0
     * 
     * @param receiver
     * @param sender
     * @param content
     * @param title
     * @param compNo
     * @return
     */
    public static String getContents(String title, String linkTitle, List resultList)
	{
		QueryBuffer contents = new QueryBuffer();
		
		contents.append("<html><head></head><table width='100%' style='font-family:'���� ���','Malgun Gothic','����',Dotum,Arial,Helvetica,sans-serif; font-size:12px; margin:10px auto; border-collapse:collapse; border:1px solid #ccc;'>");
		contents.append(" <caption style='padding:6px 10px; border-collapse:collapse; border:1px solid #ccc; font-size:20px; border:none; color:#000; font-weight:bold;'>");
		contents.append(title);
		contents.append("</caption>");
		contents.append("<tr>");
		contents.append("   <td align='right' >");
		contents.append("       <a target='_blank' style='font-size:14px;' href='" + MwareConfig.getDreamUrl() + "'>");
		contents.append("        "+linkTitle+"");
		contents.append("   </td>");
		contents.append("</tr>");
		contents.append("<tr>");
		contents.append("   <td>");
		
		
		contents.append("<table width='100%' style='font-family:'���� ���','Malgun Gothic','����',Dotum,Arial,Helvetica,sans-serif; font-size:12px; margin:20px 10px auto; border-collapse:collapse; border:1px solid #ccc;'>");
        contents.append("<tbody>");
		
		for (int i=0;i<resultList.size(); i++){
			Map map = (Map) resultList.get(i);
			Set key = map.keySet();
			if(i==0)
			{
				contents.append("<TR height='22'>");
				for(Iterator it = key.iterator(); it.hasNext();)
				{
					contents.append("<TH  style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append((String)it.next());
					contents.append("</TH>");
				}
				contents.append("</TR>");
			}
			
			contents.append("<TR height='22'>");
			for(Iterator it = key.iterator(); it.hasNext();)
			{
				String keyName = (String)it.next();
				String valueName = String.valueOf(map.get(keyName));
				
				contents.append("<td height='22' align='left' style='padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
				contents.append(valueName);
				contents.append("</TD>");
			}
			
			contents.append("</TR>");
		}
		contents.append("</tbody>");
		contents.append("</table>");
		
		
		contents.append("    </td>");
		contents.append(" </tr>");
		contents.append("</table>");
		contents.append("</html>");
		return contents.toString();
	}
}

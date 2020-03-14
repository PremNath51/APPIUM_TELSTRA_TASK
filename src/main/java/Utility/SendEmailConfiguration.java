package Utility;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import Utility.ExtentRepotEx;

public class SendEmailConfiguration 
{
  	    FolderZipClass fz=new FolderZipClass();
	

	   public static void sendEmailWithAttachments(String host, final String userName, String[] toAddress, String subject, String message, String[] attachFiles, String port, String password) throws AddressException, MessagingException 
	   {
	        // sets SMTP server properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", port);
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.user", userName);
	        properties.put("mail.password", password);
	 
	        // creates a new session with an authenticator
	        
	        
	          @SuppressWarnings("unused")
			  Authenticator auth = new Authenticator() 
	          {
	                public PasswordAuthentication getPasswordAuthentication() 
	                {
	                   return new PasswordAuthentication(userName,password);
	                }
	            };
	      
	        
	        Session session = Session.getInstance(properties);
	        
	        // This Functions compose the new email for the test report for the session
	        Message msg = new MimeMessage(session);
	 
	        msg.setFrom(new InternetAddress(userName));
	        InternetAddress[] toAddresses1 = new InternetAddress[toAddress.length];
	        
	        for(int i=0;i<toAddress.length;i++)
	        {
	        	toAddresses1[i] = new InternetAddress(toAddress[i]);
	        }
	        // Function will add the recepients in TO list
	        msg.setRecipients(Message.RecipientType.TO, toAddresses1);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	 
	        // Function will creates single message body part contents
	        MimeBodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(message, "text/html");
	 
	        // Function will creates multiple body part contents
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);
	 
	        // Addded the images/screenshots for the attachment
	        if (attachFiles != null && attachFiles.length > 0) 
	        {
	            for (String filePath : attachFiles) 
	            {
	                MimeBodyPart attachPart = new MimeBodyPart();
	 
	                try 
	                {
	                    attachPart.attachFile(filePath);
	                }
	                catch (Exception ex)
	                {
	                    ex.printStackTrace();
	                }
	 
	                multipart.addBodyPart(attachPart);
	            }
	        }
	 
	        // Add multiple part of email content
	        msg.setContent(multipart);
	 
	        // Email send button function call action
	        Transport.send(msg);
	 
	    }
	 public void sendEmail()
	 {
		 String host ="http://smtp.live.com";
		 @SuppressWarnings("unused")
		 String port = "587";
		 String mailFrom = "rpremnath51@live.com";
		 @SuppressWarnings("unused")
		 String password = ""; // If send the report then define the password over here

	   // Report detailed message information for the automation
		     String[] mailTo = new String[3];
		     mailTo[0] = "rpremnath51@live.com";

	    
	    String subject = "Wipro Mobile Automation Report";
	    String message = "Wipro mobile automation report for UAT Environment";

        String reportPath="/Users/premnathrajasekaran/Wipro-Workspace/Test/Reports/Report_"+ExtentRepotEx.timestamp;
	    String[] attachFiles = new String[2];
	    attachFiles[0] = reportPath+"."+"zip";
	  
	    
	    try ƒ
	    {
	    	fz.ZipFolder_method();
	    	Thread.sleep(8000);
	    	sendEmailWithAttachments(host, mailFrom, mailTo,
	        subject, message, attachFiles);
	        System.out.println("Successfully mobile automation email has been sent.");
	    } catch (Exception ex) 
	      {
	         System.out.println("Technical difficulties occured could not send the email. For more information reach rpremnath51@live.com");
	         ex.printStackTrace();
	      }
	}
	private void sendEmailWithAttachments(String host, String mailFrom, String[] mailTo, String subject, String message,
			String[] attachFiles) {
		// TODO Auto-generated method stub
		
	}
}

package com.mail;


import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App {
    public static void main(String[] args) {
        String message = "This is a test email from Java.";
        String subject = "Testing Email Sending";
//        String to = "thesachindhakad@gmail.com"; // Replace with the recipient's email address
        String to = "xyz@gmail.com"; // Replace with the recipient's email address
        String from = "abcd@gmail.com"; // Replace with your gmail email address

//        sendEmail(message, subject, to, from);
        sendAttach(message,subject,to,from);
}

	//this is responsible to send the message with attachment
	private static void sendAttach(String message, String subject, String to, String from) {

		//Variable for gmail
		String host="smtp.hostinger.com";;
		
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES "+properties);
		
		//setting important information to properties object
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		
		//Step 1: to get the session object..
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {				
				return new PasswordAuthentication("user id ", "password");
			}
			
			
			
		});
		
		session.setDebug(true);
		
		//Step 2 : compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);
		
		try {
		
		//from email
		m.setFrom(from);
		
		//adding recipient to message
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		//adding subject to message
		m.setSubject(subject);
	
		
		//attachement..
		
		//file path
		String path="C:\\Users\\Dell\\Desktop\\SachinNagarCV.pdf";
		
		
		MimeMultipart mimeMultipart = new MimeMultipart();
		//text
		//file
		
		MimeBodyPart textMime = new MimeBodyPart();
		
		MimeBodyPart fileMime = new MimeBodyPart();
		
		try {
			
			textMime.setText(message);
			
			File file=new File(path);
			fileMime.attachFile(file);
			
			
			mimeMultipart.addBodyPart(textMime);
			mimeMultipart.addBodyPart(fileMime);
		
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		m.setContent(mimeMultipart);
		
		
		//send 
		
		//Step 3 : send the message using Transport class
		Transport.send(m);
		
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	
		System.out.println("Sent success...................");
		
		
	}
    
    
    private static void sendEmail(String message, String subject, String to, String from) {
        String host = "smtp.hostinger.com"; // Replace with the correct SMTP server for Hostinger

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587"); // Use 587 for TLS
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Use TLS

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("user id ", "password");
            }
        });
        session.setDebug(true);

        MimeMessage mimeMessage = new MimeMessage(session);

        try {
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            Transport.send(mimeMessage);

            System.out.println("Email sent successfully to: " + to);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to send the email: " + e.getMessage());
        }
    }
}


















































//
//import java.util.Properties;
//
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
///**
// * Hello world!
// *
// */
//public class App 
//{
//    public static void main( String[] args )
//    {
//        System.out.println( "Statted program ......." );
//        
//        String message = "sachin nage send a message ....";
//        String subject = "testing ...";
//        
//        String to = "thesachindhakad@gmail.com";
//        String from = "sachindhakad568@gmail.com";
//        
//        sendMail(message , subject , to , from);
//        
//    }
//
//	private static void sendMail(String message, String subject, String to, String from) {
//		String host = "smtp.mail.com";
//		
//		Properties properties = System.getProperties();
//		System.out.println("Properties" +  properties);
//		
//		properties.put("smtp.mail.host", host);
//		properties.put("smtp.mail.port", "465");
//		properties.put("mail.smtp.ssl.enable", "true");
//		properties.put("smtp.mail.auth", "true");
//		
//		
//		Session session = Session.getInstance(properties, new Authenticator() {
//
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				// TODO Auto-generated method stub
//				return new PasswordAuthentication("sachindhakad568@gmail.com", "Trust_4_u");
//			}
//			
//			
//		});
//		session.setDebug(true);
//		
//		MimeMessage mimeMessage = new MimeMessage(session);
//		
//		try {
//			mimeMessage.setFrom(from);
//			
//			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//			
//			mimeMessage.setSubject(subject);
//			
//			mimeMessage.setText(message);
//			
//			Transport.send(mimeMessage);
//
//			System.out.println("send success.............."+mimeMessage);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//}

package com.pizza.mail;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.pizza.model.vo.PizzaMenu;

public class SendMail {
    public void gmailSend() {
        String user = "ID@gmail.com"; 		// GMail 계정 입력
        String password = "Password";   	// SMTP 패스워드

        String to = "ID@gmail.com";			// GMail 계정 입력

        // SMTP 서버 정보를 설정한다.
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true"); 
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try (ObjectInputStream ois = 
				new ObjectInputStream(new BufferedInputStream(new FileInputStream("pizza.txt")));) {
        	
        	MimeMessage message = new MimeMessage(session);
        	
        	String str_Arr[] = (String[]) ois.readObject();
        	StringBuilder sb = new StringBuilder();
        	String next = "\n";
        	
        	for(int i = 0; i < str_Arr.length; i++) {
        		sb.append(str_Arr[i]);        		
        		sb.append(next);
        	}
        	
        	message.setText(sb.toString());

            message.setFrom(new InternetAddress(user));

            //수신자메일주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 

            // Subject
            message.setSubject("주문 내역");

            // send the message
            Transport.send(message); ////전송
            System.out.println("가게에 성공적으로 전달되었습니다 !");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
			e.printStackTrace();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}

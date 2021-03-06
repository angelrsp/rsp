package ec.edu.uce.silsag.commons.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailUtil {

	private static final Logger log = LoggerFactory
			.getLogger(MailUtil.class);
	
	 private final Properties properties = new Properties();
	    private Session session;
	    
	    
	    private void init() {
	        properties.put("mail.smtp.host", "smtp.gmail.com");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.port", 587);
	        properties.put("mail.smtp.mail.sender", "silsag.fod@gmail.com");
	        properties.put("mail.smtp.mail.sender.name", "SILSAG");
	        properties.put("mail.smtp.password", "admin.fod2014");
	        properties.put("mail.smtp.user", "silsag.fod@gmail.com");
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true"); 
	        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");


	        session = Session.getDefaultInstance(properties);
	    }
	    
	    public void send(String destino,String asunto, String mensaje) {
	        init();
	        try {
	        	log.info("send");
	        	log.info("send "+destino);
	        	
	        	MimeMessage message = new MimeMessage(session);
	            
	            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender"),(String) properties.get("mail.smtp.mail.sender.name")));
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
	            message.setSubject(asunto);
	            message.setText(mensaje,"ISO-8859-1","html");
	            
	            Transport t = session.getTransport("smtp");
	            log.info("conectar");
	            t.connect((String) properties.get("mail.smtp.user"), (String) properties.get("mail.smtp.password"));
	            t.sendMessage(message, message.getAllRecipients());
	            t.close();
	            log.info("cerrar");
	        } catch (MessagingException e) {
	        	log.info(e.toString());
	            return;
	        } catch (UnsupportedEncodingException e) {
	        	log.info(e.toString());
			}
	    }
	
}

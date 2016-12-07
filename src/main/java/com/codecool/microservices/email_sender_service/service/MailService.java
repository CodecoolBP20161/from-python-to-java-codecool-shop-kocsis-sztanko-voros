package com.codecool.microservices.email_sender_service.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {
    private static MailService ourInstance = new MailService();

    public static MailService getInstance() {
        return ourInstance;
    }

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    static final String emailUsername = "testerzh1234@gmail.com";
    static final String emailPassword = "chillcoders";


    public static Properties setUp() {
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        return mailServerProperties;
    }

    public static void sendMail(String subject, String email, String username) throws AddressException, MessagingException {
        String message = String.format("This is a letter for %1$s.", username);
        getMailSession = Session.getDefaultInstance(setUp(), null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        generateMailMessage.setSubject(subject);
        generateMailMessage.setContent(message, "text/html");

        Transport transport = getMailSession.getTransport("smtp");

        transport.connect("smtp.gmail.com", emailUsername , emailPassword);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}

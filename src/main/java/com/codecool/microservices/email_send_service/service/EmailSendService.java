package com.codecool.microservices.email_send_service.service;

import org.json.JSONObject;
import spark.utils.IOUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    public static void sendMail(String subject, String email, String username) throws MessagingException, IOException {
        InputStream is = new FileInputStream("src/main/java/com/codecool/microservices/email_send_service/service/email_content/EmailsBySubject.json");
        JSONObject json = new JSONObject(IOUtils.toString(is));
        String message = null;
        for (int i = 0; i < json.getJSONArray("emails").length(); i++){
            if (json.getJSONArray("emails").getJSONObject(i).get("subject").equals("welcome")) {
                message = String.format(json.getJSONArray("emails").getJSONObject(i).get("body").toString(), username);
            }
        }
        assert message != null;
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

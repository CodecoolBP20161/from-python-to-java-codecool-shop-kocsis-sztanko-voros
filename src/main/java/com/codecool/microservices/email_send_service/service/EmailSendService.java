package com.codecool.microservices.email_send_service.service;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.utils.IOUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class EmailSendService {
    private static EmailSendService ourInstance = new EmailSendService();

    public static EmailSendService getInstance() {
        return ourInstance;
    }

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    static final String emailUsername = "testerzh1234@gmail.com";
    static final String emailPassword = "chillcoders";
    static final String SERVICE_URL = "http://localhost:60001";


    public static Properties setUp() {
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        return mailServerProperties;
    }

    public void sendEmail(String subject, String email, String username) throws MessagingException, IOException {
        String message = getMessageBySubject(subject, username);
        getMailSession = Session.getDefaultInstance(setUp(), null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        generateMailMessage.setSubject(subject);
        generateMailMessage.setContent(message, "text/html");

        Transport transport = getMailSession.getTransport("smtp");

        transport.connect("smtp.gmail.com", emailUsername, emailPassword);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }

    public String getMessageBySubject(String subject, String username) throws IOException {
        InputStream is = new FileInputStream("src/main/java/com/codecool/microservices/email_send_service/service/email_content/EmailsBySubject.json");
        JSONObject json = new JSONObject(IOUtils.toString(is));
        String message = null;
        for (int i = 0; i < json.getJSONArray("emails").length(); i++) {
            if (json.getJSONArray("emails").getJSONObject(i).get("subject").equals(subject)) {
                message = String.format(json.getJSONArray("emails").getJSONObject(i).get("body").toString(), username);
            }
        }
        return message;
    }

    public void sendEmailOrders() throws IOException, MessagingException, URISyntaxException {
        URI getURI = new URIBuilder(SERVICE_URL + "/api/email").build();
        JSONObject json = new JSONObject(execute(getURI));
        JSONArray emails = json.getJSONArray("emails");
        for (int i = 0; i < emails.length(); i++) {
            String subject = emails.getJSONObject(i).get("subject").toString();
            String email = emails.getJSONObject(i).get("email").toString();
            String username = emails.getJSONObject(i).get("username").toString();
            String id = emails.getJSONObject(i).get("id").toString();
            sendEmail(subject, email, username);
            URI statusURI = new URIBuilder(SERVICE_URL + "/api/changestatus").addParameter("id", id).build();
            execute(statusURI);
        }
    }

    private String execute(URI uri) throws IOException, URISyntaxException {
        return Request.Get(uri).execute().returnContent().asString();
    }
}

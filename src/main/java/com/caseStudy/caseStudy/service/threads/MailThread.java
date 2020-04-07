package com.caseStudy.caseStudy.service.threads;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class MailThread implements Runnable {

    private String email;
    private String subject;
    private String content;


    @Override
    public void run() {
        try{
            //send mail
            sendMail();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setSendingMail(String email,String subject,String content){
        this.email=email;
        this.subject=subject;
        this.content=content;
    }

    private void sendMail() throws Exception{

        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        Session session=Session.getInstance(properties,new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                //get company email and password
                try {
                    return new PasswordAuthentication(Credentials.getCompanyEmail(),Credentials.getCompanyPassword());
                } catch (IOException e) {
                    e.printStackTrace();

                    return null;
                }
            }
        });

        Message message=new MimeMessage(session);
        message.setFrom(new InternetAddress(Credentials.getCompanyEmail(),false));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.email));
        message.setSubject(subject);
        message.setSentDate(new Date());

        MimeBodyPart mimeBodyPart=new MimeBodyPart();
        mimeBodyPart.setContent(content,"text/html");

        Multipart multipart=new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);

    }
}

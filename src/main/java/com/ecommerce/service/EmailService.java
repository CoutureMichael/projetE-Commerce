package com.ecommerce.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailService {

    // Mailtrap credentials
    private final String username = "43471023fa1e9a";
    private final String password = "f278fc90d75ca0";

    public void envoyerEmail(String destinataire,
                             String sujet,
                             String contenu) {

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        props.put("mail.smtp.port", "2525");

        Session session = Session.getInstance(
                props,
                new Authenticator() {

                    @Override
                    protected PasswordAuthentication
                    getPasswordAuthentication() {

                        return new PasswordAuthentication(
                                username,
                                password
                        );
                    }
                }
        );

        try {

            Message message =
                    new MimeMessage(session);

            message.setFrom(
                    new InternetAddress("test@ecommerce.com")
            );

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinataire)
            );

            message.setSubject(sujet);

            message.setText(contenu);

            Transport.send(message);

            System.out.println(
                    "Email envoyé avec succès !"
            );

        } catch (MessagingException e) {

            throw new RuntimeException(e);
        }
    }
}

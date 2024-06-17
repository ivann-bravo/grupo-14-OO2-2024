package com.unla.grupo14.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.unla.grupo14.services.IEmailService;

@Service
public class EmailService implements IEmailService{

    @Autowired
    private JavaMailSender mailSender;

    public void enviarAlertaReabastecimiento(String mensaje) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("admin@example.com"); // Cambia esto por el correo real del administrador
        mailMessage.setSubject("Alerta de Reabastecimiento de Stock");
        mailMessage.setText(mensaje);
        mailSender.send(mailMessage);
    }
}

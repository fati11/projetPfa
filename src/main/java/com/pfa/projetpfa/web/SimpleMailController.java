package com.pfa.projetpfa.web;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.pfa.projetpfa.DAO.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

@RestController
public class SimpleMailController {
    @Autowired
    private JavaMailSender sender;

    @RequestMapping(value ="/sendMail",method = RequestMethod.POST)
    public Email sendMail(@RequestBody Email email) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
Email email1=new Email();
        try {
            helper.setTo(email.getEmail());
            email1.setEmail(email.getEmail());
            helper.setText(email.getTexte());
            email1.setTexte(email.getTexte());
            helper.setSubject(email.getSubject());
            email1.setSubject(email.getSubject());
        } catch (MessagingException e) {
            e.printStackTrace();
            return email1;
        }
        sender.send(message);
        return email1;

    }
}

package com.pfa.projetpfa.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfa.projetpfa.entities.Contact;
import com.pfa.projetpfa.entities.Utilisateur;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Email {
    @Id @GeneratedValue
    private Long id;
    private String email;
    private String texte;
    private String subject;
    private Date date;
    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private Utilisateur utilisateur;
    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private Contact contact;

    public Email() {
    }

    public Email(String email, String texte, String subject, Date date, Utilisateur utilisateur) {
        this.email = email;
        this.texte = texte;
        this.subject = subject;
        this.date = date;
        this.utilisateur = utilisateur;
    }
}

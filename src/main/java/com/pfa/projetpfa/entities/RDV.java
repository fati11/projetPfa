package com.pfa.projetpfa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class RDV {
    @Id @GeneratedValue
    Long id;
    private String sujet;
    private String lieu;
    private Date date;
    private String time;
    private String description;
    private String duree;
    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private Utilisateur utilisateur;
    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private Contact contact;


    public RDV(String sujet, String lieu, Date date, String duree, Utilisateur utilisateur) {
        this.sujet = sujet;
        this.lieu = lieu;
        this.date = date;
        this.duree = duree;
        this.utilisateur = utilisateur;
    }

    public RDV() {
    }
}

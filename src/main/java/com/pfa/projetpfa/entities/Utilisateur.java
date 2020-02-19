package com.pfa.projetpfa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "id")
public class Utilisateur extends User{
    private String nom;
    private String prenom;
    private String sexe;
    private String telephone;
    private Date dateNaissance;
    private String mobile;
    private String titre;
    private String email;
    private String type;
    private String fax;
    private String secteur;
    private String statut;
    private String note;
    private String assigneA;
    private boolean nonMail;
    private String adressePostale;
    private String ville;
    private String pays;
    private String codePostale;
    private String etatDep;
    private Date dateCreation;
    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String sexe, String telephone, Date dateNaissance, String mobile, String titre, String email, String fax, String secteur, String statut, String note, String assigneA, boolean nonMail, String adressePostale, String ville, String pays, String codePostale, String etatDep , String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.mobile = mobile;
        this.titre = titre;
        this.email = email;
        this.fax = fax;
        this.secteur = secteur;
        this.statut = statut;
        this.note = note;
        this.assigneA = assigneA;
        this.nonMail = nonMail;
        this.adressePostale = adressePostale;
        this.ville = ville;
        this.pays = pays;
        this.codePostale = codePostale;
        this.etatDep = etatDep;
        this.password = password;
    }
}

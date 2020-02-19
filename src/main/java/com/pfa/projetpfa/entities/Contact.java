package com.pfa.projetpfa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@PrimaryKeyJoinColumn(name="id")
public class Contact extends User{
    private String nom;
    private String prenom;
    private String telephone;
    private String sourceProspect;
    private String type;
    private String interessePar;
    private String fax;
    private String fonction;
    private Date dateNaissance;
    private String email;
    private String assigneA;
    private String adressePrincipal;
    private String ville;
    private String pays;
    private String codePostale;
    private String description;

    public Contact() {
    }
    public Contact(String nom, String prenom, String telephone, String sourceProspect, String type, String interessePar, String fax, String fonction, Date dateNaissance, String email, String assigneA, String adressePrincipal, String ville, String pays, String codePostale, String description) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.sourceProspect = sourceProspect;
        this.type = type;
        this.interessePar = interessePar;
        this.fax = fax;
        this.fonction = fonction;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.assigneA = assigneA;
        this.adressePrincipal = adressePrincipal;
        this.ville = ville;
        this.pays = pays;
        this.codePostale = codePostale;
        this.description = description;
    }
}

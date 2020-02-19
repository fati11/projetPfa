package com.pfa.projetpfa.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;
@Entity
@Data
@PrimaryKeyJoinColumn(name="id")
public class Client extends User {
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

    public Client(String username, String password) {
        super(username, password);
    }

    public Client() {
    }
}

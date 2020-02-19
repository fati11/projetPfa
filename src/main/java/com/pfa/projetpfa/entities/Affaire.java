package com.pfa.projetpfa.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Affaire implements Serializable {
    @Id @GeneratedValue
    Long id;
    private String NomAffaire;
    private String RelatifA;
    private Double montant;
    private Date dateEcheance;
    private String assigneA;
    private int probabilite;
    private String phaseVente;
    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private Contact contact;

    public Affaire(String nomAffaire, String relatifA, Double montant, Date dateEcheance, String assigneA, int probabilite, String phaseVente, Contact contact) {
        NomAffaire = nomAffaire;
        RelatifA = relatifA;
        this.montant = montant;
        this.dateEcheance = dateEcheance;
        this.assigneA = assigneA;
        this.probabilite = probabilite;
        this.phaseVente = phaseVente;
        this.contact = contact;
    }

    public Affaire() {
    }
}

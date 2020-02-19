package com.pfa.projetpfa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = -7446162716367847201L;
    @Id
    @Column(name="REFERENCE")
    private String productid;
    @Column(name="DESIGNATION",unique = true)
    private String designation;
    @Column(name="PRICE")
    private double price;
    @Column(name = "QUANTITY")
    private int quantity;
    @Lob
    @Column(name = "PRODUCTIMAGE")
    private byte[] productimage;
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}
package com.pfa.projetpfa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Inheritance(strategy= InheritanceType.JOINED)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    protected String username;
    protected String password;
    protected Date dateCreation;
    @JsonIgnore
    @OneToOne(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Role role;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

}

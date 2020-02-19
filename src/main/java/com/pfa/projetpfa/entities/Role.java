package com.pfa.projetpfa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
public class Role implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String roleName;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}

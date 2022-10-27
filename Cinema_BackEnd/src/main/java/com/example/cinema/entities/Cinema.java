package com.example.cinema.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cinema implements Serializable {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double logitude,altitude,latitude;
    private int nombreSalles;
    @ManyToOne
    private Ville ville;

    @OneToMany(mappedBy="cinema")
    private Collection<Salle> salles;

}

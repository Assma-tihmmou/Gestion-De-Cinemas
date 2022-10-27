package com.example.cinema.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Projection implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private Date date;
    private double prix;
    @ManyToOne
    private Film film;
    @ManyToOne
    private Salle salle;
    @OneToMany (mappedBy = "projection")
    private Collection<Ticket> tickes;
    @ManyToOne
    private Seance seance;

}

package com.example.cinema.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;

@Projection(
        name = "proj" ,
        types ={com.example.cinema.entities.Projection.class}
)
public interface projectionProj {
    public long getId();
    public Salle getSalle();
    public Film getFilm();
    public long getPrix();
    public Date getDate();
    public Collection<Ticket> getTickes();
    public Seance getSeance();

}

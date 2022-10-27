package com.example.cinema.dao;

import com.example.cinema.entities.Cinema;
import com.example.cinema.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200")
public interface TicketRepository extends JpaRepository<Ticket,Long> {
}

package com.example.cinema.services;

import com.example.cinema.dao.FilmRepository;
import com.example.cinema.dao.TicketRepository;
import com.example.cinema.entities.Film;
import com.example.cinema.entities.Ticket;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaController {
    @Autowired

     FilmRepository filmRepository;
    @Transactional

    @GetMapping(path="/image/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
   public byte[] images(@PathVariable(name = "id") long id) throws IOException {

        Film film;
        film = filmRepository.findById(id).get();
        String image=film.getPhoto();
       // File file = new File(System.getProperty("D:/ete2022/src/main/resources/static/images/"+image)+".jpg");
        Path path = Paths.get("D:/ete2022/Cinema_BackEnd/src/main/resources/static/images/" +image+".jpg");
        return Files.readAllBytes(path);
    }


    @Autowired
     TicketRepository ticketRepository;
    @Transactional
    @CrossOrigin(origins = "http://localhost:4200")

    @PostMapping(path="/ticketsPayes")
    public List<Ticket> ticketsPayes(@RequestBody TicketForm ticketForm) {
        List<Ticket> listTickets = new ArrayList<>();
        ticketForm.getTickets().forEach(t -> {
            Ticket ticket = ticketRepository.findById(t).get();
            System.out.println(ticketForm.getTickets());
            ticket.setNomClient(ticketForm.getNomClient());
            ticket.setReserve(true);
            ticket.setCodedPayement(ticketForm.getCodePayement());
            ticketRepository.save(ticket);
            listTickets.add(ticket);
        });
        return listTickets;
    }
}
@Data
class TicketForm{
    public String nomClient;
    public Long codePayement;
    public List<Long> tickets;
}
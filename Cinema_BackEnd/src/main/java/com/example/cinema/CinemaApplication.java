package com.example.cinema;

import com.example.cinema.entities.Film;
import com.example.cinema.entities.Ticket;
import com.example.cinema.services.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {
    @Autowired
    private ICinemaInitService cinemaInitService;
    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }
    @Autowired
    private RepositoryRestConfiguration  repositoryRestConfiguration;
    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Film.class);
        repositoryRestConfiguration.exposeIdsFor(Ticket.class);

        cinemaInitService.initVilles();
        cinemaInitService.initCinemas();
        cinemaInitService.initSalles();
        cinemaInitService.initPlaces();
        cinemaInitService.initSeances();
        cinemaInitService.initCategories();
        cinemaInitService.initFilms();
        cinemaInitService.initProjections();
        cinemaInitService.initTickets();


    }
}

package com.example.cinema.services;

import com.example.cinema.dao.*;
import com.example.cinema.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class ICinemaInitServiceImpl implements ICinemaInitService{

    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private     ProjectionRepository projectionRepository;
    @Autowired
    private CategorieRepository categorieRepository;


    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(v->{
            Stream.of("Megarama","Founoun","Chahrazad","Doualiz").forEach(nameCinema->{
                Cinema cinema = new Cinema();
                cinema.setName(nameCinema);
                cinema.setVille(v);
                cinema.setNombreSalles(3+(int)(Math.random()*7));
                cinemaRepository.save(cinema);

            });
        });

    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(s->{
            for(int i=0; i<s.getNombrePlaces();i++){
                Place place = new Place();
                place.setSalle(s);
                place.setNumeroPlace(i+1);
                placeRepository.save(place);
            }
        });

    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(c->{
            for(int i=0;i<c.getNombreSalles();i++)
            {
                Salle salle = new Salle();

                salle.setName("salle"+(i+1));
                salle.setCinema(c);
                salle.setNombrePlaces(10+(int)(Math.random()*10));
                salleRepository.save(salle);
            }
        });

    }

    @Override
    public void initVilles() {
        Stream.of("Agadir","Casablanca","Tanger","istanbul").forEach(nameVille->{
           Ville ville = new Ville();
           ville.setName(nameVille);
           villeRepository.save(ville);
        });

    }


    @Override
    public void initFilms() {
        List<Categorie> categories = categorieRepository.findAll();
        double[] duree =new double[] {1,2.5,1.3};
        Stream.of("Game of Thrones","Segneur des anneaux","Viking","Spider man").forEach(f->{
            Film film = new Film();
            film.setTitre(f);
            film.setPhoto(f);//setPhoto(f.replaceAll(" ",""))
            film.setDuree(duree[new Random().nextInt(duree.length)]);
            film.setCategorie(categories.get(new Random().nextInt(categories.size())));
            filmRepository.save(film);

        });
    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(p->{
            p.getSalle().getPlaces().forEach(place->{
                Ticket ticket =new Ticket();
                ticket.setPrix(p.getPrix());
                ticket.setPlace(place);
                ticket.setProjection(p);
                ticket.setReserve(false);
                ticketRepository.save(ticket);
            });
        });
    }

    @Override
    public void initCategories() {
        Stream.of("Action","Drama","Fiction").forEach(c->{
            Categorie categorie = new Categorie();
            categorie.setName(c);
            categorieRepository.save(categorie);
        });

    }

    @Override
    public void initProjections() {

        double[] prices = new double[] {200,300,100,400};
            villeRepository.findAll().forEach(v->{
               v.getCinemas().forEach(c->{
                   List<Film> films = filmRepository.findAll();
                    c.getSalles().forEach(s->{
                        int index = new Random().nextInt(films.size());
                        Film film = films.get(index);
                        seanceRepository.findAll().forEach(seance->{
                                Projection projection = new Projection();
                                projection.setFilm(film);
                                projection.setSalle(s);
                                projection.setDate(new Date());
                                projection.setSeance(seance);
                                projection.setPrix(prices[new Random().nextInt(prices.length)]);
                                projectionRepository.save(projection);
                            });
                    });
                });
            });
    }

    @Override
    public void initSeances() {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("12:00","04:00","08:00","22:00").forEach(s->{
            Seance seance = new Seance();
            try {
                seance.setHeureDebut(dateFormat.parse(s));
                seanceRepository.save(seance);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }


}

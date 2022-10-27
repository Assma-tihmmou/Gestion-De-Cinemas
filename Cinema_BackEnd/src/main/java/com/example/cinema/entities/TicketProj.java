package com.example.cinema.entities;
@org.springframework.data.rest.core.config.Projection(name="ticketproj",types = Ticket.class)
public interface TicketProj {
    public Long getId();
    public String getNomClient();
    public Double getPrix();
    public Long getcodedPayement();
    public Boolean getReserve();
    public Place getPlace();
    public Projection getProjection();
}

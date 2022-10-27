import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CinemaService } from '../services/cinema.service';

@Component({
  selector: 'app-cinema',
  templateUrl: './cinema.component.html',
  styleUrls: ['./cinema.component.css']
})
export class CinemaComponent implements OnInit {
  public villes: any;
  public cinemas:any;
  public currentVille: any;
  public currentCinema: any;
  public salles:any;
  public currentSeance:any;
  public projection:any;
public currentSalle: any;
public form=false;
public currentSalle2: any;
public selectedTicket= new Array();


  constructor(public cinemaService:CinemaService) { }

  ngOnInit(): void {

    this.cinemaService.getVilles().subscribe(data=> {
          this.villes=data
        },err=>{
            console.log(err)
        })



  }

  public OngetCinemas( ville: any){
    this.salles=undefined;

    this.currentVille = ville;
    this.cinemaService.getCinemas(ville).subscribe(data=> {
      this.cinemas=data
    },err=>{
        console.log(err)
    })
  }

  public OngetSalles(cinema:any){
    this.salles=undefined;

    this.currentCinema = cinema
    this.cinemaService.getSalles(cinema).subscribe(data=> {
      this.salles=data
      this.salles._embedded.salles.forEach((s: any)=> {
        this.cinemaService.getProjections(s).
        subscribe(data=> {
          s.projection=data
        },err=>{
            console.log(err)
        })
      });
    },err=>{
        console.log(err)
    })
  }

  public OngetTickets(projection:any,salle:any){
    this.currentSalle=salle;
    this.currentSeance=projection;
    this.projection=projection;
    //console.log(this.projection.id);
                this.cinemaService.getTickets(projection.id).subscribe(
                  (ticket:any)=>{
                    this.projection.tickets=ticket;
                    console.log(ticket)                      }
                      ,err=>{
                    console.log(err)
                  }
                );
   }

   public onSelectTicket(t:any,salle:any){
    this.currentSalle2=salle;
    this.form =true;
    console.log(this.selectedTicket)
      if(t.selected){
        t.selected=false;
        this.selectedTicket.splice(this.selectedTicket.indexOf(t),1);
      }else{
        t.selected=true;
        this.selectedTicket.push(t);
      }
  }

  public OngetClass(t:any){
    let str="btn place ";
      if (t.selected){
        str+="btn-warning"
      }
      if(t.reserve){
        str+="btn-danger"
      }
      else{
        str+="btn-success"
      }
      return str;
  }
  public PayerTicket(f:any){
    let tickets: any[] =[];
    this.selectedTicket.forEach((t)=>{
     tickets.push(t.id);
    })
    f.tickets =tickets
    this.cinemaService.OnPayerticket(f).subscribe(data=> {
      alert("Tickets Reservés avec succès");
    },err=>{
        console.log(err)
    })

  }




}


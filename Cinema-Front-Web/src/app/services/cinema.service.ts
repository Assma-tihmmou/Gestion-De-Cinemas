import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CinemaService {


public host:string = "http://localhost:8080";

  constructor(private http:HttpClient) { }

  public  getVilles(){
      return this.http.get(this.host+"/villes");

  }

  public getCinemas(ville:any){
    return this.http.get(ville._links.cinemas.href);
  }

  public getSalles(cinema:any){
    return this.http.get(cinema._links.salles.href);
  }

  public getProjections(salle:any){
     const link=salle._links.projections.href.replace("{?projection}","");
    return this.http.get(link+"?projection=proj");
  }

public getTickets(p:any){
  const link=this.host+"/projections/"+p+"/tickes?projection=ticketproj";
  return this.http.get(link);}

public getPlaces(p:any){
  const link=this.host+"/projections/"+p+"/tickes?projection=ticketproj";
  return this.http.get(link);
}

public OnPayerticket(f: any) {
  console.log(f)
  return this.http.post(this.host+"/ticketsPayes",f);
}
}

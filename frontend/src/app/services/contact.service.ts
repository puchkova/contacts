import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
@Injectable({
  providedIn: 'root'
})
export class ContactService {

  private baseUrl = 'http://localhost:8080/contacts';

  constructor(private http: HttpClient) { }

  public getContacts(): Observable<any>{
    return this.http.get(`${this.baseUrl}`)
  }

  public getContactsByName(name: string){
    return this.http.get(`${this.baseUrl}`+ "?name=" + name)
  }
}

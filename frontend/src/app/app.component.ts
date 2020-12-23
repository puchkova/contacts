import {Component, OnInit} from '@angular/core';
import {ContactService} from './services/contact.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  contacts: any;
  name: string = "";
  totalRecords: number = 0;
  page: number = 1;
  title: string = "";

  constructor(private service: ContactService) {
    this.contacts = new Array<any>();
  }

  ngOnInit() {
     let resp = this.service.getContacts();
     resp.subscribe((data) => this.contacts = data);
  }

  public findContactsByName() {
    let resp = this.service.getContactsByName(this.name);
    resp.subscribe((data) => this.contacts = data);
  }
}

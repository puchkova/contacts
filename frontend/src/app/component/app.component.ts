import {Component, OnInit} from '@angular/core';
import {ContactService} from '../service/contact.service'

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
    this.getContacts()
  }

  public getContacts() {
    let resp = this.service.getContacts(this.name);
    resp.subscribe((data) => {
      this.contacts = data;
      this.totalRecords = data.length;
      this.page = 1;
    })
  }

  public clearSearchField() {
    this.name = "";
  }
}

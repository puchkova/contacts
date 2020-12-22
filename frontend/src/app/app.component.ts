import {Component, OnInit} from '@angular/core';
import {ContactService} from './services/contact.service'
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  contacts:any;
  name: string = "";
  totalRecords: Number | undefined;
  page: Number = 1;

  constructor(private service:ContactService){
    this.contacts = new Array<any>()
  }
  ngOnInit() {
    this.service.getData().subscribe((data) => {
      console.log(data);
      this.contacts = data;
      this.totalRecords = data.length;
    })
  }
  public findContactByName(){
    let resp = this.service.getContactByName(this.name);
    resp.subscribe((data)=>this.contacts=data);

  }
}

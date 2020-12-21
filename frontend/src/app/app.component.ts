import { Component } from '@angular/core';
import {ContactService} from './services/contact.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  data: Array<any>
  totalRecords: Number | undefined
  page: Number=1

  constructor(private contact:ContactService){
    this.data = new Array<any>()
  }
  ngOnInit() {
    this.contact.getData().subscribe((data) => {
      console.log(data)
      this.data = data
      this.totalRecords = data.length
    })
  }
}

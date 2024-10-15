import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'] 
})
export class AppComponent implements OnInit {
  phrase: string = "Working...";


  constructor() {}

  ngOnInit(): void {
      console.log('APP OK')
  }
}

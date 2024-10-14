import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'] 
})
export class AppComponent implements OnInit {
  phrase: string = "Working...";


  constructor() {}

  async ngOnInit(): Promise<void> {
    // WE ARE JUST FAST TESTING IF THE CORS OF OUR BACKEND IS OK WITH FETCH API
   try {
    const response = await fetch('https://jagarv-jq5o.onrender.com/health', {
      method: 'GET'
    })

    const data = await response.json()
    console.log('data from java server', data)
   } catch(e) {
    console.log(e)
   }
  }
}

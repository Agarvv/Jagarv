import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  phrase: string = "Working...";
  ngOnInit(): void {
    console.log('all works fine')
  }
}

import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-success-component',
  templateUrl: './success-component.component.html',
  styleUrl: './success-component.component.css'
})
export class SuccessComponentComponent {
  @Input() message: string = "All Went Ok!";
}

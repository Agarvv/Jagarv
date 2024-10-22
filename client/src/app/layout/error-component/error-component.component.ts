import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-error-component',
  templateUrl: './error-component.component.html',
  styleUrl: './error-component.component.css'
})
export class ErrorComponentComponent {
 @Input() message: string = "Something Went Wrong..."
}

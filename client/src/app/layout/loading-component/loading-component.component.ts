import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-loading-component',
  templateUrl: './loading-component.component.html',
  styleUrl: './loading-component.component.css'
})
export class LoadingComponentComponent {
  @Input() message: string = 'Operación exitosa!';
}

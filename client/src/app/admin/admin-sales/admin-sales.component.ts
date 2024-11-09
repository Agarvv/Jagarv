import { Component, OnInit } from '@angular/core';
import { SalesService } from '@services/admin/sales/sales.service';
import { Sales } from '@models/Sales/Sales';
import { Store } from '@ngrx/store';
import { setError, clearMessages } from '@store/admin/admin.actions';


@Component({
  selector: 'app-admin-sales',
  templateUrl: './admin-sales.component.html',
  styleUrl: './admin-sales.component.css'
})
export class AdminSalesComponent implements OnInit {
  sales: Sales[] = [];
   constructor(private salesService: SalesService, private store: Store) {
      
   }
   
   ngOnInit(): void {
       this.loadSales(); 
       
   }

   loadSales(): void {
    this.store.dispatch(clearMessages()); // just in case
     this.salesService.getSales().pipe(
     // i will add here something in the future if necessary
     ).subscribe((data: Sales[]) => {
       this.sales = data;
       console.log("Sales loaded", data);
     }, (error) => {
       console.log("error", error) // debug
       this.store.dispatch(setError({ errorMessage: "Something went wrong..." }));
     })
   }
   
   
}

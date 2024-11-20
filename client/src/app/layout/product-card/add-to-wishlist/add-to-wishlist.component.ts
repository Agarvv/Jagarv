import { Component, Input } from '@angular/core';
import { WishlistService } from '@services/wishlist/wishlist.service'; 

@Component({
  selector: 'app-add-to-wishlist',
  templateUrl: './add-to-wishlist.component.html',
  styleUrl: './add-to-wishlist.component.css'
})
export class AddToWishlistComponent {
    @Input() productId: number | null = null; 
    
    
   constructor(private wishlistService: WishlistService) {} 
   
   addOrRemoveToWishlist(): void
   {
       if(this.productId) 
       {
           this.wishlistService.addOrRemoveToWishlist(this.productId)
           .subscribe((data) => {
               console.log('add wishlist ok', data)
           }, (error) => {
               console.error('add wishlist f', error)
           })
       }
   }
}

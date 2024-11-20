import { Component, Input } from '@angular/core';
import { WishlistService } from '@services/wishlist/wishlist.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-to-wishlist',
  templateUrl: './add-to-wishlist.component.html',
  styleUrls: ['./add-to-wishlist.component.css']
})
export class AddToWishlistComponent {
  @Input() productId: number | null = null;
  @Input() inWishlist: boolean | false = false; 

  constructor
  (
      private wishlistService: WishlistService,
      private router: Router
  )
   {
       
   }

  addOrRemoveToWishlist(): void {
    if (this.productId !== null && this.productId > 0) {

      const body = { productId: this.productId };

      this.wishlistService.addOrRemoveToWishlist(body)
        .subscribe((data) => {
          console.log('add wishlist ok', data);
          this.router.navigate(['/wishlist'])
        }, (error) => {
          console.error('add wishlist f', error);
        });
    } else {
      console.error('productId no es válido');
    }
  }
}
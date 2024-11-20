import { Component, Input } from '@angular/core';
import { WishlistService } from '@services/wishlist/wishlist.service';

@Component({
  selector: 'app-add-to-wishlist',
  templateUrl: './add-to-wishlist.component.html',
  styleUrls: ['./add-to-wishlist.component.css']
})
export class AddToWishlistComponent {
  @Input() productId: number | null = null;

  constructor(private wishlistService: WishlistService) {}

  addOrRemoveToWishlist(): void {
    if (this.productId !== null && this.productId > 0) {

      const body = { productId: this.productId };

      this.wishlistService.addOrRemoveToWishlist(body)
        .subscribe((data) => {
          console.log('add wishlist ok', data);
        }, (error) => {
          console.error('add wishlist f', error);
        });
    } else {
      console.error('productId no es válido');
    }
  }
}
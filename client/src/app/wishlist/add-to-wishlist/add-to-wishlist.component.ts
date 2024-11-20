
import { NumberSymbol } from '@angular/common';
import { Component, Input } from '@angular/core';
import { WishlistService } from '@services/wishlist/wishlist.service';

@Component({
  selector: 'app-add-to-wishlist',
  templateUrl: './add-to-wishlist.component.html',
  styleUrl: './add-to-wishlist.component.css'
})
export class AddToWishlistComponent {
  @Input() productId: number | null = null;
  isWishlisted: boolean = false;

  constructor(private wishlistService: WishlistService) {}

  addOrRemoveToWishlist() {
   if(this.productId) {

    this.wishlistService.addOrRemoveToWishlist(this.productId)
    .subscribe((data: any) => {
      console.log('Product wishlist successfully', data);
    }, (error) => {
      console.error(error);
    })

   }
  }
}

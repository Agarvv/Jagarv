import { Component, OnInit } from '@angular/core';
import { WishlistService } from '@services/wishlist/wishlist.service'; 
import { Store } from '@ngrx/store';
import { setError, clearMessages } from '@store/admin/admin.actions';
import { Product } from '@models/Product';
import { Wishlist } from '@models/wishlist/Wishlist'

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css'] 
})
export class WishlistComponent implements OnInit {
  products: Product[] = []; 

  constructor(
    private wishlistService: WishlistService,
    private store: Store
  ) {}

  ngOnInit(): void {
    this.store.dispatch(clearMessages());

    this.wishlistService.getUserWishlist()
      .pipe(
      
      )
      .subscribe(
        (data: Wishlist) => { 
          console.log('User wishlist:', data);
        },
        (error: any) => { 
          console.error('Error fetching wishlist:', error);
          this.store.dispatch(setError({
            errorMessage: 'Failed to fetch the wishlist. Please try again later.'
          }));
        }
      );
  }
}
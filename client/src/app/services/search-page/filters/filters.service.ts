import { Injectable } from '@angular/core';
import { Product } from '@models/Product';
import { Filters } from '@models/search/Filters';

@Injectable({
  providedIn: 'root'
})
export class FiltersService {

  constructor() { }

  applyFilters(products: Product[], filters: Filters): Product[] 
  {
     return products.filter((product) => {
        product.category.name == filters.category
        product.price >= filters.minPrice! && product.price <= filters.maxPrice!
     })
  }
}

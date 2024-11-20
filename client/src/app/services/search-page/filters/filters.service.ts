import { Injectable } from '@angular/core';
import { Product } from '@models/Product';
import { Filters } from '@models/search/Filters';

@Injectable({
  providedIn: 'root'
})
export class FiltersService {

  constructor() { }

  applyFilters(products: Product[], filters: Filters): Product[] {
    console.log('products', products); 
    console.log('filters', filters);

    return products.filter((product) => {
      const matchesCategory = filters.category ? product.category.name === filters.category : true;
      const matchesPrice = (filters.minPrice == null || product.price >= filters.minPrice) &&
                           (filters.maxPrice == null || product.price <= filters.maxPrice);
      return matchesCategory && matchesPrice;
    });
  }
}

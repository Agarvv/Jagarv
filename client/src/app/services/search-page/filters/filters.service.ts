import { Injectable } from '@angular/core';
import { Product } from '@models/Product';
import { Filters } from '@models/search/Filters';

@Injectable({
  providedIn: 'root'
})
export class FiltersService {

  constructor() { }

  applyFilters(products: Product[], filters: Filters): Product[] {
    console.log('products', products); // debug
    console.log('filters', filters);

    return products.filter((product) => {
      return (!filters.category || product.category.name === filters.category) &&
             (!filters.minPrice || product.price >= filters.minPrice) &&
             (!filters.maxPrice || product.price <= filters.maxPrice) &&
             (!filters.color || product.category.attributes.some
              (attr => attr.name === 'Color' && attr.options.some
                (option => option.value.toLowerCase() === filters.color?.toLowerCase())));
    });
  }
}

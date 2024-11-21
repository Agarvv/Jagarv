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
      return (!filters.category || product.category.name === filters.category) && // category filter

             (!filters.minPrice || product.price >= filters.minPrice) && // min price filter 

             (!filters.maxPrice || product.price <= filters.maxPrice) && // max price filter

             (!filters.color || product.category.attributes.some // color filter
              (attr => attr.name === 'Color' && attr.options.some
                (option => option.value.toLowerCase() === filters.color?.toLowerCase()))) &&

                // storage filter
                (!filters.storage || product.category.attributes.some(
                  attr => attr.name == 'Storage' && attr.options.some(
                    option => option.value.toLowerCase() == filters.storage?.toLowerCase()
                  )
                )) &&

                // ram filter 
                (!filters.ram || product.category.attributes.some(attr =>
                  attr.name == 'RAM' && attr.options.some(option => 
                    option.value.toLowerCase() == filters.ram?.toLowerCase()
                  )
                ));

                // i will add here more if necesary
    });
  }
}

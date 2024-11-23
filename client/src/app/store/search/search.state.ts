import { Product } from '@models/Product'; 

export interface SearchState {
  allProducts: Product[];
  filteredResults: Product[];
}

export const initialState: SearchState = {
  allProducts: [],
  filteredResults: [],
}
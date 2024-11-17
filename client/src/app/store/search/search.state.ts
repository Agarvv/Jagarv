import { Product } from '@models/Product'; 

export interface SearchState {
    results: Product[] 
}

export const initialState: SearchState = {
    results: [] 
}
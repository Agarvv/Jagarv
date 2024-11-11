import { createReducer, on } from '@ngrx/store';
import { setProduct } from './product-details.actions'; // Importar la acción
import { ProductDetailsState, initialState } from './product-details.state';

export const productDetailsReducer = createReducer(
  initialState,
  on(setProduct, (state, { product }) => ({
    ...state,  
    product,   
  }))
);

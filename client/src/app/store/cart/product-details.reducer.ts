import { createReducer, on } from '@ngrx/store';
import { setProduct } from './product-details.actions';
import { ProductDetailsState, initialState } from './product-details.state';

export const productDetailsReducer = createReducer(
  initialState,
  on(setProduct, (state, { product }) => {
    console.log('Product set in reducer:', product); // debug
    return {
      ...state,
      product,
    };
  })
);
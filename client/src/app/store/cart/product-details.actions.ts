import { createAction, props } from '@ngrx/store';
import { Product } from '@models/Product';

export const setProduct = createAction(
  '[Global] Set Product',
  props<{ product: Product | null }>() 
);


import { createAction, props } from '@ngrx/store';
import { Cart } from '@models/cart/Cart'; 

export const setCart = createAction(
  '[Cart] Set Cart',
  props<{ cart: Cart }>()
);

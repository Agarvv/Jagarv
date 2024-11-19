
import { createReducer, on } from '@ngrx/store';
import { setCart } from './cart.actions'; 
import { CartState, initialCartState } from './cart.state';  

export const cartReducer = createReducer(
  initialCartState,
  on(setCart, (state, { cart }) => ({ ...state, cart }))  
);
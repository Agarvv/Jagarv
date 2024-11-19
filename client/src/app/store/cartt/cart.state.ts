import { createAction, createReducer, on, props } from '@ngrx/store';
import { Cart } from '@models/cart/Cart'; 

// Estado inicial
export interface CartState {
  cart: Cart | null;
}

export const initialCartState: CartState = {
  cart: null,
};

// Acciones
export const setCart = createAction('[Cart] Set Cart', props<{ cart: Cart }>());

// Reducer
export const cartReducer = createReducer(
  initialCartState,
  on(setCart, (state, { cart }) => ({ ...state, cart })) 
);
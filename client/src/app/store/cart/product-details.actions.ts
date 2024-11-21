import { createAction, props } from '@ngrx/store';
import { Product } from '@models/Product';

export const setProduct = createAction(
  '[Global] Set Product',
  props<{ product: Product | null }>() 
);

export const addOrUpdateAttribute = createAction(
  '[Product Details] Add or Update Attribute',
  props<{ attribute: { attributeName: string; attributeOptionId: number } }>()
);

export const setOrUpdateQuantity = createAction(
    'Add Or Update Quantity',
    props<{ quantity: number }>()
)
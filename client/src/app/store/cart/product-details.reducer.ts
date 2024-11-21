import { createReducer, on } from '@ngrx/store';
import { setProduct, addOrUpdateAttribute, setOrUpdateQuantity } from './product-details.actions';
import { ProductDetailsState, initialState } from './product-details.state';

export const productDetailsReducer = createReducer(
  initialState,
  on(setProduct, (state, { product }) => ({
    ...state,
    product
  })),

  on(addOrUpdateAttribute, (state, { attribute }) => {
    // if user already selected, for example, an attribute with the name of 'color', updates that wanted attribute.
    const existingIndex = state.attributes.findIndex(
      (attr) => attr.attributeName === attribute.attributeName
    );

    const updatedAttributes =
      existingIndex !== -1
        ? state.attributes.map((attr, index) =>
            index === existingIndex ? attribute : attr
          )
        : [...state.attributes, attribute];
    // else adds new attribute to the list of selected attributes.
    return {
      ...state,
      attributes: updatedAttributes
    };
  }),

  on(setOrUpdateQuantity, (state, { quantity }) => ({
    ...state,
    quantity
  }))
);
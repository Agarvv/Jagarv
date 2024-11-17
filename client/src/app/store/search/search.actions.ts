import { createAction, props } from '@ngrx/store';

import { Product } from '@models/Product';

export const setResults = createAction(
  'Set Results',
  props<{ results: Product[] }>()
);
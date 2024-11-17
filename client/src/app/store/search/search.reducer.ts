import { createReducer, on } from '@ngrx/store';
import { setResults } from './search.actions';
import { SearchState, initialState } from './search.state';

export const searchReducer = createReducer(
  initialState,
  on(setResults, (state, { results }) => ({ ...state, results }))
);
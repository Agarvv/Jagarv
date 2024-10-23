import { createReducer, on } from '@ngrx/store';
import { setLoading, setSuccess, setError, clearMessages } from './admin.actions';
import { AdminState, initialState } from './admin.state';

export const adminReducer = createReducer(
  initialState,
  on(setLoading, (state, { isLoading }) => ({ ...state, loading: isLoading })),
  on(setSuccess, (state, { successMessage }) => ({ ...state, success: successMessage, loading: false })),
  on(setError, (state, { errorMessage }) => ({ ...state, error: errorMessage, loading: false })),
  on(clearMessages, (state) => ({ ...state, error: null, success: null }))
);

import { createAction, props } from '@ngrx/store';

export const setLoading = createAction('[Global] Set Loading', props<{ isLoading: boolean }>());
export const setSuccess = createAction('[Global] Set Success', props<{ successMessage: string | null }>());
export const setError = createAction('[Global] Set Error', props<{ errorMessage: string | null }>());
export const clearMessages = createAction('[Global] Clear Messages');

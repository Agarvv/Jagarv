import { createFeatureSelector, createSelector } from '@ngrx/store';
import { AdminState } from './admin.state';

export const selectAdminState = createFeatureSelector<AdminState>('admin'); // 'admin' debería coincidir con el nombre del feature state

export const selectLoading = createSelector(
    selectAdminState,
    (state: AdminState) => state.loading
);

export const selectError = createSelector(
    selectAdminState,
    (state: AdminState) => state.error
);

export const selectSuccess = createSelector(
    selectAdminState,
    (state: AdminState) => state.success
);

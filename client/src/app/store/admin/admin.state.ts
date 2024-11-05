export interface AdminState {
    loading: boolean;
    error: string | null;
    success: string | null;
}

export const initialState: AdminState = {
    loading: false,
    error: null,
    success: null,
  };
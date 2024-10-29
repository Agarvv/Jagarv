export interface AdminState {
    loading: boolean;
    error: string | null;
    success: string | null;
}

export const initialState: AdminState = {
    loading: true,
    error: null,
    success: null,
  };
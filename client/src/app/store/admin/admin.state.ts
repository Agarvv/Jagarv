export interface AdminState {
    loading: boolean;
    error: string;
    success: string | null;
}

export const initialState: AdminState = {
    loading: false,
    error: "Testing",
    success: null,
  };
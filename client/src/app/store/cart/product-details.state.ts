import { Product } from "@models/Product"

export interface ProductDetailsState {
    product: Product | null;
}

export const initialState: ProductDetailsState = {
    product: null
    // i will add more soon
}
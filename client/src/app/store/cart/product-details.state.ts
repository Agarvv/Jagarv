import { Product } from "@models/Product"


export interface ProductDetailsState {
    // this is the product that is being displayed on product details page
    product: Product | null;
    
    // this is the list of product attributes that the user selected for his order
    // like, 'Sara wants her Smartphone with attribute RAM with value 16GB'.
    attributes: { attributeName: string; attributeOptionId: number }[];
    
    // this is the product quantity 
    quantity: number;
  }
  
  export const initialState: ProductDetailsState = {
    product: null,
    attributes: [],
    quantity: 1
};
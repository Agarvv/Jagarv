import { Product } from '@models/Product'


export interface Cart 
{
    cartId: number,
    products: Product[] 
}
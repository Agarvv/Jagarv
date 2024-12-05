import { Product } from '@models/Product'
import { CartItem } from '@models/cart/CartItem'


export interface Cart 
{
    cartId: number,
    items: CartItem[] 
}
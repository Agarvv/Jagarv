import { CartItemOption } from './CartItemOption'


export interface CartItem {
    productId: number,
    pictures: string[],
    price: number,
    quantity: number,
    title: string,
    options: CartItemOption[]
}
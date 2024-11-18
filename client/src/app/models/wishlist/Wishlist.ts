import { Product } from '@models/Product'

export interface Wishlist {
    wishlistId: number,
    products: Product[]
}
import { Attribute } from './Attribute'

export interface ProductVariants {
    id: number,
    price: number,
    stock: number,
    images: string[],
    attributes: Attribute[]
}
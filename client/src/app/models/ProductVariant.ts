import { Attribute } from './Attribute'

export interface ProductVariant {
    id: number,
    price: number,
    stock: number,
    images: string[],
    attributes: Attribute[]
}
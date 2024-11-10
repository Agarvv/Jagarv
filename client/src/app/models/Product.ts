import { ProductVariant } from './ProductVariant'

export interface Product {
    id: number,
    title: string,
    description: string,
    category: string,
    featured: boolean,
    stock: number,
    date: string
    pictures: string[],
    price: number,
    variants: ProductVariant[]
} 
import { ProductVariant } from './ProductVariant'
import { Attribute } from './Attribute' 
import { ProductCategory } from './ProductCategory'

export interface Product {
    category: ProductCategory
    id: number,
    title: string,
    description: string,
    featured: boolean,
    stock: number,
    date: string
    pictures: string[],
    price: number,
    variants: ProductVariant[],
} 
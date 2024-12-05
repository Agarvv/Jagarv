import { ProductVariant } from './ProductVariant'
import { Attribute } from './Attribute' 
import { ProductCategory } from './ProductCategory'
import { ProductOpinion } from './ProductOpinion'
import { AttributeOption } from './AttributeOption'

export interface Product {
    category: ProductCategory
    productId: number,
    title: string,
    description: string,
    featured: boolean,
    stock: number,
    date: string
    pictures: string[],
    price: number,
    variants: ProductVariant[],
    opinions: ProductOpinion[],
    inWishlist: boolean,
    inCart: boolean,
    options: AttributeOption[]
} 
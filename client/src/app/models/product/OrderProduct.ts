import { Product } from "@models/Product"
import { AttributeOption } from "@models/AttributeOption"

export interface OrderProduct {
    id: number,
    options: AttributeOption[],
    product: Product,
    quantity: number
}
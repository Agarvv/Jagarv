import { OrderProduct } from "@models/product/OrderProduct"

export interface Order {
    id: number,
    adress: string,
    amount: number,
    products: OrderProduct[],
    status: string
}
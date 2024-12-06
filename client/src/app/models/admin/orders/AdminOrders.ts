import { AdminOrderProducts } from "./AdminOrderProduct";

export interface AdminOrders {
    id: number,
    amount: number,
    status: string,
    adress: string,
    products: AdminOrderProducts[]
}
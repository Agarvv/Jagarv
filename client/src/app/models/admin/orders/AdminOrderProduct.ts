import { Product } from '@models/Product';
import { AdminOrderProductAttribute } from './AdminOrderPoductAttribute';


export interface AdminOrderProducts {
    id: number,
    quantity: number,
    product: Product,
    options: AdminOrderProductAttribute[]
}
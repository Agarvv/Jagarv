import { Attribute } from "./Attribute";


export interface ProductCategory {
   id: number,
   name: string,
   attributes: Attribute[]
}
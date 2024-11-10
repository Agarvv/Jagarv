import { AttributeOption } from './AttributeOption'

export interface Attribute {
    id: number,
    name: string,
    options: AttributeOption[]
}
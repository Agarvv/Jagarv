export interface Product {
    id: number,
    title: string,
    description: string,
    category: string,
    pictures: string[],
    price: number // I know in my API Java DTO i have BigDecimal. 
}
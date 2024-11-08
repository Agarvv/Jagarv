export interface Sales {
  id: number, // just the id of the sale
  amount: number, // $500, $196.521.715... $3........ $1.5
  method: string // paypal, credit card, stripe...
}
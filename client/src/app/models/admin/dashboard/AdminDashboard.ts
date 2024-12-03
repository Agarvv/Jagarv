
export interface AdminDashboard {
  orderCountByMonth: [number, number][]; // first number is month, and second is the number of orders.
  usersToday: number;
  ordersToday: number;
  mostOrderedProducts: [number, string, number][];
  totalEarningsToday: number;
}
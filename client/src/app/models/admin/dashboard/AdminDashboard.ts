export interface AdminDashboard {
  orderCountByMonth: [number, number][]; 
  usersToday: number;
  ordersToday: number;
  mostOrderedProducts: [number, string, string, number, number, number][]; 
  totalEarningsToday: number;
  totalIncome: number;
}
import { BestSeller } from "@models/product/BestSeller";

export interface AdminDashboard {
  orderCountByMonth: [number, number][]; 
  usersToday: number;
  ordersToday: number;
  mostOrderedProducts: BestSeller[];  // best seller array
  totalEarningsToday: number;
  totalIncome: number;
}
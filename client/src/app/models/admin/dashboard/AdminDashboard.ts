import { BestSeller } from "@models/product/BestSeller";

export interface AdminDashboard {
  orderCountByMonth: [number, number][]; 
  usersToday: number;
  ordersToday: number;
  mostOrderedProducts: BestSeller[]; 
  totalEarningsToday: number;
  totalIncome: number;
}
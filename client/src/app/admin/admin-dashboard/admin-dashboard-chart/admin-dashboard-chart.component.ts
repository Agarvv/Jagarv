import { Component, Input, OnChanges } from '@angular/core';
import { AdminDashboard } from '@models/admin/dashboard/AdminDashboard';
import { ChartOptions, ChartData } from 'chart.js';

@Component({
  selector: 'app-admin-dashboard-chart',
  templateUrl: './admin-dashboard-chart.component.html',
  styleUrls: ['./admin-dashboard-chart.component.css'],
})
export class AdminDashboardChartComponent implements OnChanges {
  @Input() dashboard: AdminDashboard | null = null;

  chartOptions: ChartOptions = {
    responsive: true,
    scales: {
      x: {
        title: {
          display: true,
          text: 'Month',
        },
      },
      y: {
        title: {
          display: true,
          text: 'Orders',
        },
      },
    },
  };

  chartData: ChartData = {
    labels: [] as string[], 
    datasets: [
      {
        label: 'Sales Per Month',
        data: [] as number[],
        fill: false,
        borderColor: 'rgba(75,192,192,1)',
        tension: 0.1,
      },
    ],
  };

  ngOnChanges() {
    if (this.dashboard?.orderCountByMonth) {
      const months = this.dashboard.orderCountByMonth.map(([month]) =>
        ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'][month - 1]
      );

      const orderCounts = this.dashboard.orderCountByMonth.map(([, orders]) => orders);

      this.chartData.labels = months;
      this.chartData.datasets[0].data = orderCounts;
    }
  }
}
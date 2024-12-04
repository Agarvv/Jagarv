import { Component, Input, OnChanges } from '@angular/core';
import { AdminDashboard } from '@models/admin/dashboard/AdminDashboard';

@Component({
  selector: 'app-admin-dashboard-chart',
  templateUrl: './admin-dashboard-chart.component.html',
  styleUrls: ['./admin-dashboard-chart.component.css'],
})
export class AdminDashboardChartComponent implements OnChanges {
  @Input() dashboard: AdminDashboard | null = null;

  chartType = 'bar'; 
  chartData = {
    labels: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun'],
    datasets: [
      {
        label: 'Sales Per Month',
        data: [120, 150, 180, 200, 170, 220], 
        backgroundColor: [
          'rgba(75, 192, 192, 0.2)',
          'rgba(54, 162, 235, 0.2)',
          'rgba(255, 206, 86, 0.2)',
          'rgba(75, 192, 192, 0.2)',
          'rgba(153, 102, 255, 0.2)',
          'rgba(255, 159, 64, 0.2)',
        ],
        borderColor: [
          'rgba(75, 192, 192, 1)',
          'rgba(54, 162, 235, 1)',
          'rgba(255, 206, 86, 1)',
          'rgba(75, 192, 192, 1)',
          'rgba(153, 102, 255, 1)',
          'rgba(255, 159, 64, 1)',
        ],
        borderWidth: 1,
      },
    ],
  };

  /* ngOnChanges() {
    if (this.dashboard?.orderCountByMonth) {
      const months = this.dashboard.orderCountByMonth.map(([month]) =>
        ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'][month - 1]
      );

      const orderCounts = this.dashboard.orderCountByMonth.map(([, orders]) => orders);

      this.chartData.labels = months;
      this.chartData.datasets[0].data = orderCounts;
    }
  } */
}
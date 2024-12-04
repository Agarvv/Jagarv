import { Component, Input, OnChanges } from '@angular/core';
import { AdminDashboard } from '@models/admin/dashboard/AdminDashboard';
import { EChartsOption } from 'echarts';

@Component({
  selector: 'app-admin-dashboard-chart',
  templateUrl: './admin-dashboard-chart.component.html',
  styleUrls: ['./admin-dashboard-chart.component.css'],
})
export class AdminDashboardChartComponent implements OnChanges {
  @Input() dashboard: AdminDashboard | null = null;

  chartOptions: EChartsOption = {
    title: {
      text: 'Sales Per Month',
    },
    xAxis: {
      type: 'category',
      data: [] as string[], 
    },
    yAxis: {
      type: 'value',
    },
    series: [
      {
        data: [] as number[],
        type: 'line',
      },
    ],
  };

  ngOnChanges() {
    if (this.dashboard?.orderCountByMonth) {
      const months = this.dashboard.orderCountByMonth.map(([month]) =>
        ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'][month - 1]
      );

      const orderCounts = this.dashboard.orderCountByMonth.map(([, orders]) => orders);

      (this.chartOptions.xAxis as { data: string[] }).data = months; 
      (this.chartOptions.series as { data: number[] }[])[0].data = orderCounts; 
    }
  }
}
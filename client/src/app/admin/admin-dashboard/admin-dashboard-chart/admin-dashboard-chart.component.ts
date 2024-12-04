import { Component, Input, OnChanges } from '@angular/core';
import { AdminDashboard } from '@models/admin/dashboard/AdminDashboard'
import { EChartsOption } from 'echarts'

@Component({
  selector: 'app-admin-dashboard-chart',
  templateUrl: './admin-dashboard-chart.component.html',
  styleUrls: ['./admin-dashboard-chart.component.css']
})
export class AdminDashboardChartComponent implements OnChanges {
  @Input() dashboard: AdminDashboard | null = null;

  chartOptions: EChartsOption = {
    title: {
      text: 'Sales Per Month',
    },
    xAxis: {
      type: 'category',
      data: [],  
    },
    yAxis: {
      type: 'value',
    },
    series: [
      {
        data: [], 
        type: 'line',
      },
    ],
  };

  ngOnChanges() {
    if (this.dashboard && this.dashboard.orderCountByMonth) {
        
      const months = this.dashboard.orderCountByMonth.map(item => {
        return this.getMonthName(item[0]);  
      });
      const orderCounts = this.dashboard.orderCountByMonth.map(item => item[1]);

      this.chartOptions.xAxis.data = months;
      this.chartOptions.series[0].data = orderCounts;
    }
  }

  getMonthName(monthNumber: number): string {
    const months = [
      'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'
    ];
    return months[monthNumber - 1] || '';  
  }
}
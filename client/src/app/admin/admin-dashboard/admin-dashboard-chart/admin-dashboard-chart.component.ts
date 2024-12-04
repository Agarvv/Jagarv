import { Component, Input, AfterViewInit, ViewChild, ElementRef, OnInit } from '@angular/core';
import { AdminDashboard } from '@models/admin/dashboard/AdminDashboard';
import { Chart, ChartType } from 'chart.js'; 

@Component({
  selector: 'app-admin-dashboard-chart',
  templateUrl: './admin-dashboard-chart.component.html',
  styleUrls: ['./admin-dashboard-chart.component.css'],
})
export class AdminDashboardChartComponent implements AfterViewInit, OnInit {
  @Input() dashboard: AdminDashboard | null = null;
  @ViewChild('chartCanvas') private chartCanvas!: ElementRef<HTMLCanvasElement>;

  chart: any;  
  chartType: ChartType = 'bar'; 

  chartData = {
    labels: [] as string[],  
    datasets: [
      {
        label: 'Sales Per Month',
        data: [] as number[],  
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

  chartOptions = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top' as const,  
      },
    },
    scales: {
      x: {
        beginAtZero: true,
      },
      y: {
        beginAtZero: true,
      },
    },
  };

  ngAfterViewInit() {
    const canvas = this.chartCanvas.nativeElement;

    this.chart = new Chart(canvas, {
      type: this.chartType,
      data: this.chartData,
      options: this.chartOptions,
    });
  }

  getMonth(i: number) {
    let months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    return months[i];
  }

  ngOnInit(): void {
    if (this.dashboard && this.dashboard.orderCountByMonth) {
      const labels = this.dashboard.orderCountByMonth.map(([monthIndex, orderCount]) => {
        const monthName = this.getMonth(monthIndex - 1);
        console.log('month name', monthName)
        return monthName;
      });

      this.chartData.labels = labels;

      const data = this.dashboard.orderCountByMonth.map(([monthIndex, orderCount]) => orderCount);
      this.chartData.datasets[0].data = data;
    }
  }
}

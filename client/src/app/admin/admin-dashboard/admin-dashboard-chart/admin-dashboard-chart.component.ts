import { Component, Input, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { AdminDashboard } from '@models/admin/dashboard/AdminDashboard';
import { Chart, ChartType } from 'chart.js'; 

@Component({
  selector: 'app-admin-dashboard-chart',
  templateUrl: './admin-dashboard-chart.component.html',
  styleUrls: ['./admin-dashboard-chart.component.css'],
})
export class AdminDashboardChartComponent implements AfterViewInit {
  @Input() dashboard: AdminDashboard | null = null;
  @ViewChild('chartCanvas') private chartCanvas!: ElementRef<HTMLCanvasElement>;

  chart: any;  
  chartType: ChartType = 'bar'; 

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
}

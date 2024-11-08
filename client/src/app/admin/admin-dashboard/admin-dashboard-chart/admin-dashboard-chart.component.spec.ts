import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDashboardChartComponent } from './admin-dashboard-chart.component';

describe('AdminDashboardChartComponent', () => {
  let component: AdminDashboardChartComponent;
  let fixture: ComponentFixture<AdminDashboardChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminDashboardChartComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminDashboardChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

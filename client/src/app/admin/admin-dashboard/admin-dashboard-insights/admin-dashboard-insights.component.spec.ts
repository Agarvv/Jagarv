import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDashboardInsightsComponent } from './admin-dashboard-insights.component';

describe('AdminDashboardInsightsComponent', () => {
  let component: AdminDashboardInsightsComponent;
  let fixture: ComponentFixture<AdminDashboardInsightsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminDashboardInsightsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminDashboardInsightsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

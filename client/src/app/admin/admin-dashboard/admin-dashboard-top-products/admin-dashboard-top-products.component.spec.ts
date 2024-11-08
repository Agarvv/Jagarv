import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDashboardTopProductsComponent } from './admin-dashboard-top-products.component';

describe('AdminDashboardTopProductsComponent', () => {
  let component: AdminDashboardTopProductsComponent;
  let fixture: ComponentFixture<AdminDashboardTopProductsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminDashboardTopProductsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminDashboardTopProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

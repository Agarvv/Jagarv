import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminOrderInTransitButtonComponent } from './admin-order-in-transit-button.component';

describe('AdminOrderInTransitButtonComponent', () => {
  let component: AdminOrderInTransitButtonComponent;
  let fixture: ComponentFixture<AdminOrderInTransitButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminOrderInTransitButtonComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminOrderInTransitButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

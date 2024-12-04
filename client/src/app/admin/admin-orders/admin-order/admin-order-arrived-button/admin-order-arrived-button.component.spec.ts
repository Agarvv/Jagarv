import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminOrderArrivedButtonComponent } from './admin-order-arrived-button.component';

describe('AdminOrderArrivedButtonComponent', () => {
  let component: AdminOrderArrivedButtonComponent;
  let fixture: ComponentFixture<AdminOrderArrivedButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminOrderArrivedButtonComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminOrderArrivedButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

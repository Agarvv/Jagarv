import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmitPaymentComponent } from './submit-payment.component';

describe('SubmitPaymentComponent', () => {
  let component: SubmitPaymentComponent;
  let fixture: ComponentFixture<SubmitPaymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SubmitPaymentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubmitPaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

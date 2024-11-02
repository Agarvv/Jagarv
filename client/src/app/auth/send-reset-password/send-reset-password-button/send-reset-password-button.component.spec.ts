import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendResetPasswordButtonComponent } from './send-reset-password-button.component';

describe('SendResetPasswordButtonComponent', () => {
  let component: SendResetPasswordButtonComponent;
  let fixture: ComponentFixture<SendResetPasswordButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SendResetPasswordButtonComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SendResetPasswordButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

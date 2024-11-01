import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResetPasswordButtonComponent } from './reset-password-button.component';

describe('ResetPasswordButtonComponent', () => {
  let component: ResetPasswordButtonComponent;
  let fixture: ComponentFixture<ResetPasswordButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ResetPasswordButtonComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResetPasswordButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmitRegisterButtonComponent } from './submit-register-button.component';

describe('SubmitRegisterButtonComponent', () => {
  let component: SubmitRegisterButtonComponent;
  let fixture: ComponentFixture<SubmitRegisterButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SubmitRegisterButtonComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubmitRegisterButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginSubmitButtonComponent } from './login-submit-button.component';

describe('LoginSubmitButtonComponent', () => {
  let component: LoginSubmitButtonComponent;
  let fixture: ComponentFixture<LoginSubmitButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginSubmitButtonComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginSubmitButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

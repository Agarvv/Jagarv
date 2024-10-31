import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TwitterAuthButtonComponent } from './twitter-auth-button.component';

describe('TwitterAuthButtonComponent', () => {
  let component: TwitterAuthButtonComponent;
  let fixture: ComponentFixture<TwitterAuthButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TwitterAuthButtonComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TwitterAuthButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

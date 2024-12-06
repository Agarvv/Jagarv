import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResponsiveWarningComponent } from './responsive-warning.component';

describe('ResponsiveWarningComponent', () => {
  let component: ResponsiveWarningComponent;
  let fixture: ComponentFixture<ResponsiveWarningComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ResponsiveWarningComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResponsiveWarningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

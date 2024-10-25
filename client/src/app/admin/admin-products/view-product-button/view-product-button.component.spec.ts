import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewProductButtonComponent } from './view-product-button.component';

describe('ViewProductButtonComponent', () => {
  let component: ViewProductButtonComponent;
  let fixture: ComponentFixture<ViewProductButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ViewProductButtonComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewProductButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

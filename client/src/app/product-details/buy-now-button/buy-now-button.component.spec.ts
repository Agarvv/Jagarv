import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuyNowButtonComponent } from './buy-now-button.component';

describe('BuyNowButtonComponent', () => {
  let component: BuyNowButtonComponent;
  let fixture: ComponentFixture<BuyNowButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BuyNowButtonComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BuyNowButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

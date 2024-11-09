import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductVariantSubmitButtonComponent } from './product-variant-submit-button.component';

describe('ProductVariantSubmitButtonComponent', () => {
  let component: ProductVariantSubmitButtonComponent;
  let fixture: ComponentFixture<ProductVariantSubmitButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProductVariantSubmitButtonComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductVariantSubmitButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

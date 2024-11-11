import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductDetailsDataComponent } from './product-details-data.component';

describe('ProductDetailsDataComponent', () => {
  let component: ProductDetailsDataComponent;
  let fixture: ComponentFixture<ProductDetailsDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProductDetailsDataComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductDetailsDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductDetailsDataAttributesComponent } from './product-details-data-attributes.component';

describe('ProductDetailsDataAttributesComponent', () => {
  let component: ProductDetailsDataAttributesComponent;
  let fixture: ComponentFixture<ProductDetailsDataAttributesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProductDetailsDataAttributesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductDetailsDataAttributesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

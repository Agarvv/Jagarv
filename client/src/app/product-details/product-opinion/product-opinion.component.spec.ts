import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductOpinionComponent } from './product-opinion.component';

describe('ProductOpinionComponent', () => {
  let component: ProductOpinionComponent;
  let fixture: ComponentFixture<ProductOpinionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProductOpinionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductOpinionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

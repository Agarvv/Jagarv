import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RemoveCartProductComponent } from './remove-cart-product.component';

describe('RemoveCartProductComponent', () => {
  let component: RemoveCartProductComponent;
  let fixture: ComponentFixture<RemoveCartProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RemoveCartProductComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RemoveCartProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

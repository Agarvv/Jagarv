import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProductVariantComponent } from './add-product-variant.component';

describe('AddProductVariantComponent', () => {
  let component: AddProductVariantComponent;
  let fixture: ComponentFixture<AddProductVariantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddProductVariantComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddProductVariantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

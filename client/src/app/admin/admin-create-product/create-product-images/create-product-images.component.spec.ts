import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateProductImagesComponent } from './create-product-images.component';

describe('CreateProductImagesComponent', () => {
  let component: CreateProductImagesComponent;
  let fixture: ComponentFixture<CreateProductImagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateProductImagesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateProductImagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

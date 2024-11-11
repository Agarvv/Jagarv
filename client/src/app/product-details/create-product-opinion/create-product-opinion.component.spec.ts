import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateProductOpinionComponent } from './create-product-opinion.component';

describe('CreateProductOpinionComponent', () => {
  let component: CreateProductOpinionComponent;
  let fixture: ComponentFixture<CreateProductOpinionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateProductOpinionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateProductOpinionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeatureProductButtonComponent } from './feature-product-button.component';

describe('FeatureProductButtonComponent', () => {
  let component: FeatureProductButtonComponent;
  let fixture: ComponentFixture<FeatureProductButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FeatureProductButtonComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeatureProductButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

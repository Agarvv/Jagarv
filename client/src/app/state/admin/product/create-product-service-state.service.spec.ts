import { TestBed } from '@angular/core/testing';

import { CreateProductServiceStateService } from './create-product-service-state.service';

describe('CreateProductServiceStateService', () => {
  let service: CreateProductServiceStateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateProductServiceStateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

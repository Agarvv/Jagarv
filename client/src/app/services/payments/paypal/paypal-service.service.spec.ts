import { TestBed } from '@angular/core/testing';

import { PaypalServiceService } from './paypal-service.service';

describe('PaypalServiceService', () => {
  let service: PaypalServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PaypalServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

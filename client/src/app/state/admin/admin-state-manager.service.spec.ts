import { TestBed } from '@angular/core/testing';

import { AdminStateManagerService } from './admin-state-manager.service';

describe('AdminStateManagerService', () => {
  let service: AdminStateManagerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminStateManagerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

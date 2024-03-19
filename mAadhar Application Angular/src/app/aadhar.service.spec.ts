import { TestBed } from '@angular/core/testing';

import { AadharService } from './aadhar.service';

describe('AadharService', () => {
  let service: AadharService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AadharService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

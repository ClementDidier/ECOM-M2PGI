import { TestBed, inject } from '@angular/core/testing';

import { BungalowService } from './bungalow.service';

describe('BungalowService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BungalowService]
    });
  });

  it('should be created', inject([BungalowService], (service: BungalowService) => {
    expect(service).toBeTruthy();
  }));
});

import { TestBed } from '@angular/core/testing';

import { Artwork } from './artwork';

describe('Artwork', () => {
  let service: Artwork;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Artwork);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

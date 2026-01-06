import { TestBed } from '@angular/core/testing';

import { AiTagging } from './ai-tagging';

describe('AiTagging', () => {
  let service: AiTagging;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AiTagging);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

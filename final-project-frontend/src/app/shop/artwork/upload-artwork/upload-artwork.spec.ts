import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadArtwork } from './upload-artwork';

describe('UploadArtwork', () => {
  let component: UploadArtwork;
  let fixture: ComponentFixture<UploadArtwork>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UploadArtwork]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UploadArtwork);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

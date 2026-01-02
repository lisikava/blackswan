import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicArtwork } from './public-artwork';

describe('PublicArtwork', () => {
  let component: PublicArtwork;
  let fixture: ComponentFixture<PublicArtwork>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicArtwork]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PublicArtwork);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtistOrders } from './artist-orders';

describe('ArtistOrders', () => {
  let component: ArtistOrders;
  let fixture: ComponentFixture<ArtistOrders>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArtistOrders]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArtistOrders);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

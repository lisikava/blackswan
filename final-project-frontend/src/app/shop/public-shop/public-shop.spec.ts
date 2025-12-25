import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicShop } from './public-shop';

describe('PublicShop', () => {
  let component: PublicShop;
  let fixture: ComponentFixture<PublicShop>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicShop]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PublicShop);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

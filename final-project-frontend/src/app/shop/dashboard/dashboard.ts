import { Component, OnInit } from '@angular/core';
import { Shop } from '../shop.model';
import { Shop as ShopService } from '../../services/shop';
import { Artwork as ArtworkService } from '../../services/artwork';
import { Artwork } from '../artwork/artwork.model';
import { Router, RouterLink } from '@angular/router';
import { Subject } from 'rxjs';
import { CommonModule } from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatChipsModule} from '@angular/material/chips';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule, MatButtonModule, RouterLink, MatCardModule, MatIconModule, MatChipsModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
    shop?: Shop;
    shop$: Subject<Shop> = new Subject<Shop>();
    artworks$: Subject<Artwork[]> = new Subject<Artwork[]>();

  constructor(
    private shopService: ShopService,
    private artworkService: ArtworkService,
    private router: Router
  ) {}

  ngOnInit() {
    this.loadArtworks();
    this.shopService.getMyShop().subscribe(
      (shop) => { if(shop) {
        this.shop$.next(shop);
      }},
      (err) => {
        // console.error(err);
        this.router.navigate(['/shop/create']);
      }
    );
  }

  editShop() {
    this.router.navigate(['/shop/edit']);
  }

  upload() {
    this.router.navigate(['/shop/upload']);
  }

  loadArtworks() {
    this.artworkService.getMyArtworks()
      .subscribe(data => this.artworks$.next(data));
  }
  
  edit(id: number) {

  }
  remove(id: number) {
    if (!confirm('Delete this artwork?')) return;
    this.artworkService.deleteArtwork(id).subscribe(() => this.loadArtworks());
  }
}

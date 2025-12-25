import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatChipsModule} from '@angular/material/chips';
import { Router, RouterLink } from '@angular/router';
import { Artwork as ArtworkService } from '../services/artwork';
import { Homepage as HomepageService } from '../services/homepage';
import { PublicArtwork } from '../shop/artwork/public-artwork.model';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-homepage',
  imports: [CommonModule, MatButtonModule, RouterLink, MatCardModule, MatIconModule, MatChipsModule],
  templateUrl: './homepage.html',
  styleUrl: './homepage.css',
})
export class Homepage implements OnInit {

  artworks?: PublicArtwork[];
  artworks$: Subject<PublicArtwork[]> = new Subject<PublicArtwork[]>();

  constructor(
      private homepageService: HomepageService,
      private artworkService: ArtworkService,
      private router: Router
    ) {}

  ngOnInit() {
    this.homepageService.getRandomArtworks().subscribe({
        next: (artworks) => {
          this.artworks$.next(artworks);
        },
        error: (err) => {
          console.error(err);
          this.router.navigate(['/']);
        }
      });
  }
  
  openShop(slug: string) {
    this.router.navigate(['/shops', slug]);
  }
}

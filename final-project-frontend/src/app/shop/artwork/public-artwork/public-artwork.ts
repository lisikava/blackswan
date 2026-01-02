import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Artwork as ArtworkService } from '../../../services/artwork';
import { PublicArtwork as Artwork } from '../public-artwork.model';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { Subject } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-public-artwork',
  imports: [MatButtonModule, MatIconModule, MatChipsModule, CommonModule],
  templateUrl: './public-artwork.html',
  styleUrl: './public-artwork.css',
})
export class PublicArtwork implements OnInit {
  artwork?: Artwork;
  artwork$: Subject<Artwork> = new Subject<Artwork>();

    constructor(
    private route: ActivatedRoute,
    private artworkService: ArtworkService,
    private router: Router
  ) {}
  ngOnInit() {
    const artworkId = this.route.snapshot.paramMap.get('artworkId')!;
        this.artworkService.getArtwork(Number(artworkId)).subscribe({
        next: (artwork) => {
          this.artwork$.next(artwork);
        },
        error: (err) => {
          this.router.navigate(['/']);
        }
      });
  }

  goToOrder() {
    const artworkId = this.route.snapshot.paramMap.get('artworkId')!;
    this.router.navigate(['/orders', artworkId]);
  }
}

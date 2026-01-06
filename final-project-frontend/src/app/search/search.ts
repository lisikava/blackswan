import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatChipsModule} from '@angular/material/chips';
import { Router, RouterLink, ActivatedRoute } from '@angular/router';
import { PublicArtwork } from '../shop/artwork/public-artwork.model';
import { Subject } from 'rxjs';
import { Search as SearchService } from '../services/search';

@Component({
  selector: 'app-search',
  imports: [CommonModule, MatButtonModule, RouterLink, MatCardModule, MatIconModule, MatChipsModule],
  templateUrl: './search.html',
  styleUrl: './search.css',
})
export class Search {
  tag!: string;
  artworks?: PublicArtwork[];
  artworks$: Subject<PublicArtwork[]> = new Subject<PublicArtwork[]>();
  constructor(
      private route: ActivatedRoute,
      private searchService: SearchService,
      private router: Router
    ) {}
  

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.tag = params['tag'];
      this.load();
    });
  }

  load() {
    this.searchService.search(this.tag).subscribe({
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

  openTag(tag: string) {
    this.router.navigate(['/search'], { queryParams: { tag } });
  }

}

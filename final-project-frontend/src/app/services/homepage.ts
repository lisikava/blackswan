import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PublicArtwork } from '../shop/artwork/public-artwork.model';

@Injectable({
  providedIn: 'root',
})
export class Homepage {
  private apiUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) {}

  getRandomArtworks(limit = 12) {
    return this.http.get<PublicArtwork[]>(
      `${this.apiUrl}/artworks/random?limit=${limit}`
    );
  }
  
}

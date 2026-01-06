import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PublicArtwork } from '../shop/artwork/public-artwork.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Search {
  private apiUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) {}

  search(tag: string, limit = 12) {
    return this.http.get<PublicArtwork[]>(
      `${this.apiUrl}/artworks/search?tag=${tag}&limit=${limit}`
    )
  }
}

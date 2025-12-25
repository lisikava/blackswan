import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Artwork as ArtworkModel } from '../shop/artwork/artwork.model';

@Injectable({
  providedIn: 'root',
})
export class Artwork {
  private apiUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) {}

    uploadArtwork(data: ArtworkDto, file: File) {
      const formData = new FormData();
      formData.append(
        'data',
        new Blob([JSON.stringify(data)], { type: 'application/json' })
      );
      formData.append('image', file);

      return this.http.post(`${this.apiUrl}/shops/me/artworks`, formData);
    }

    getMyArtworks() {
      return this.http.get<ArtworkModel[]>(`${this.apiUrl}/shops/me/artworks`);
    }

    deleteArtwork(id: number) {
      return this.http.delete(`${this.apiUrl}/artworks/${id}`);
    }
}

export interface ArtworkDto {
  title: string;
  description: string;
  price: number;
  tags: string[];
  status: 'AVAILABLE' | 'SOLD';
}

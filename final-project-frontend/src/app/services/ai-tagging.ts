import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AiTagging {
  private apiUrl = 'http://localhost:8080/ai/tags';
  constructor(private http: HttpClient) {}
  
  suggestTags(image: File) {
    const formData = new FormData();
    formData.append('image', image);
    return this.http.post<string[]>(this.apiUrl, formData);
  }
}

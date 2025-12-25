import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Shop as ShopModel } from '../shop/shop.model';


@Injectable({
  providedIn: 'root',
})
export class Shop {
  private apiUrl = 'http://localhost:8080/shops';
  constructor(private http: HttpClient) {}

  createShop(data: { name: string, description: string }): Observable<any> {
    return this.http.post(this.apiUrl, data);
  }
  getMyShop() {
    return this.http.get<ShopModel>(`${this.apiUrl}/me`);
  }
  editShop(data: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/me`, data);
  }
  getPublicShop(slug: string) {
    return this.http.get<ShopModel>(`${this.apiUrl}/${slug}`);
  }
}

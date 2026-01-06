import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order as OrderModel } from '../order/order.model';

@Injectable({
  providedIn: 'root',
})
export class Order {
  private apiUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) {}

  placeOrder(artworkId: number, data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/orders/artworks/${artworkId}`, data);
  }

  getArtistOrders() {
    return this.http.get<ArtistOrderDto[]>(`${this.apiUrl}/orders/me`);
  }

  getCustomerOrders() {
    return this.http.get<CustomerOrderDto[]>(`${this.apiUrl}/orders/placed`);
  }
  
}

export interface ArtistOrderDto {
  id: number;
  fullName: string;
  address: string;
  city: string;
  country: string;
  zipCode: string;
  phone: string;
  buyerEmail: string;
  imageUrl: string;
  status: string;
}

export interface CustomerOrderDto {
  id: number;
  fullName: string;
  address: string;
  city: string;
  country: string;
  zipCode: string;
  phone: string;
  shopName: string;
  imageUrl: string;
  status: string;
}
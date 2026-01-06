import { Component, OnInit } from '@angular/core';
import { ArtistOrderDto } from '../../services/order';
import { Order as OrderService } from '../../services/order';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-artist-orders',
  imports: [CommonModule, MatCardModule, MatButtonModule, MatIconModule],
  templateUrl: './artist-orders.html',
  styleUrl: './artist-orders.css',
})
export class ArtistOrders implements OnInit {
    orders$: Subject<ArtistOrderDto[]> = new Subject<ArtistOrderDto[]>(); 
    constructor(
      private orderService: OrderService,
      private router: Router
    ) {}
  
    ngOnInit(): void {
      this.orderService.getArtistOrders().subscribe({
        next: (orders) => {
          this.orders$.next(orders)
        },
        error: (err) => {
            console.error(err);
            this.router.navigate(['/']);
          }
      });
    }

}

import { Component, OnInit } from '@angular/core';
import { CustomerOrderDto } from '../../services/order';
import { Order as OrderService } from '../../services/order';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-customer-orders',
  imports: [CommonModule, MatCardModule, MatButtonModule, MatIconModule],
  templateUrl: './customer-orders.html',
  styleUrl: './customer-orders.css',
})
export class CustomerOrders implements OnInit {
  orders$: Subject<CustomerOrderDto[]> = new Subject<CustomerOrderDto[]>(); 
  constructor(
    private orderService: OrderService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.orderService.getCustomerOrders().subscribe({
      next: (orders) => {
        this.orders$.next(orders)
      },
      error: (err) => {
          console.error(err);
          this.router.navigate(['/']);
        }
    });
  }

  confirmReceived() {

  }

}

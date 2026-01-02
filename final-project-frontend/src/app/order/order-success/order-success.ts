import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';

@Component({
  selector: 'app-order-success',
  imports: [],
  templateUrl: './order-success.html',
  styleUrl: './order-success.css',
})
export class OrderSuccess {
  protected orderId: string;
  constructor(
    private route: ActivatedRoute,
  ) {
    this.orderId = this.route.snapshot.paramMap.get('artworkId')!;
  }

}

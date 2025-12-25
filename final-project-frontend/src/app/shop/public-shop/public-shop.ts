import { Component, OnInit } from '@angular/core';
import { Shop as ShopModel } from '../shop.model';
import { Shop as ShopService } from '../../services/shop';
import { Router, RouterLink, ActivatedRoute } from '@angular/router';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatChipsModule} from '@angular/material/chips';
import { CommonModule } from '@angular/common';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-public-shop',
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatCardModule, MatIconModule, MatChipsModule],
  templateUrl: './public-shop.html',
  styleUrl: './public-shop.css',
})
export class PublicShop implements OnInit {
    shop?: ShopModel;
    shop$: Subject<ShopModel> = new Subject<ShopModel>();

  constructor(
    private route: ActivatedRoute,
    private shopService: ShopService,
    private router: Router
  ) {}

  ngOnInit() {
    const slug = this.route.snapshot.paramMap.get('slug')!;
    this.shopService.getPublicShop(slug).subscribe({
        next: (shop) => {
          this.shop$.next(shop);
        },
        error: (err) => {
          this.router.navigate(['/']);
        }
      });
  }
}

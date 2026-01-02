import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Order as OrderService } from '../services/order';
import { Artwork as ArtworkService } from '../services/artwork';
import { PublicArtwork as Artwork } from '../shop/artwork/public-artwork.model';
import { Subject } from 'rxjs';
import { CommonModule } from '@angular/common';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';

@Component({
  selector: 'app-order',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, CommonModule, MatButtonModule, MatIconModule, MatFormFieldModule, MatInputModule],
  templateUrl: './order.html',
  styleUrl: './order.css',
})
export class Order  {
  artwork$: Subject<Artwork> = new Subject<Artwork>();
  artworkType?: string;
  orderForm: FormGroup;
  constructor(
    private route: ActivatedRoute,
    private orderService: OrderService,
    private artworkService: ArtworkService,
    private fb: FormBuilder,
    private router: Router
  ) {
    const artworkId = this.route.snapshot.paramMap.get('artworkId')!;
    this.artworkService.getArtwork(Number(artworkId)).subscribe({
      next: (artwork) => {
        this.artwork$.next(artwork);
        this.artworkType = artwork.type;
      },
      error: (err) => {
        this.router.navigate(['/']);
      }
    });

    this.orderForm = this.fb.group({
      fullName: ['', [Validators.required, Validators.minLength(3)]],
      address: ['', Validators.required],
      city: ['', Validators.required],
      country: ['', Validators.required],
      zipCode: ['', Validators.required],
      phone: ['', [Validators.required, Validators.pattern('^[0-9+ ]*$')]]
    });
  }

  onSubmit() {
    if (this.orderForm.valid || this.artworkType === 'DIGITAL') {
      const artworkId = this.route.snapshot.paramMap.get('artworkId')!; 
      this.orderService.placeOrder(Number(artworkId), this.orderForm.value as any).subscribe({
        next: (order) => {
          this.router.navigate(['/orders/success', order.id]);
        },
        error: (err) => {
          console.error('Order failed: ', err);
        },
    });
    }
  }

}

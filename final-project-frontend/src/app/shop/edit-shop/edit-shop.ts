import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import { Shop } from '../shop.model';
import { Shop as ShopService } from '../../services/shop';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-edit-shop',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatCardModule],
  templateUrl: './edit-shop.html',
  styleUrl: './edit-shop.css',
})
export class EditShop implements OnInit {
  shopForm: FormGroup;
  error$: Subject<string> = new Subject<string>();
  isSubmitting = false;
  shop?: Shop;
  shop$: Subject<Shop> = new Subject<Shop>();
  constructor(
    private fb: FormBuilder,
    private shopService: ShopService,
    private router: Router
  ) {
    this.shopForm = this.fb.group({
      name: ['', [Validators.minLength(3)]],
      description: ['', [Validators.minLength(3)]]
    });
  }

    ngOnInit() {
      this.shopService.getMyShop().subscribe(
        (shop) => { if(shop) {
          this.shop$.next(shop);
        }},
        (err) => {
          this.router.navigate(['/shop/create']);
        }
      );
    }
  onSubmit() {
    if (this.shopForm.valid) {
      this.isSubmitting = true;
      this.shopService.editShop(this.shopForm.value as any).subscribe({
        next: (response) => {
          this.router.navigate(['/dashboard']);
        },
        error: (err) => {
          this.isSubmitting = false;
          console.error('Error editing shop:', err);
          this.error$.next('Error editing shop');
        }
      });
    }
  }
  
  cancel() {
    this.router.navigate(['/dashboard']);
  }

}

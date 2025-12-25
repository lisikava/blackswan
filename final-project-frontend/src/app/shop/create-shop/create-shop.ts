import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { Shop } from '../../services/shop';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-create-shop',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatCardModule],
  templateUrl: './create-shop.html',
  styleUrl: './create-shop.css',
})
export class CreateShop {
  shopForm: FormGroup;
  error$: Subject<string> = new Subject<string>();
  isSubmitting = false;
  constructor(
    private fb: FormBuilder,
    private shopService: Shop,
    private router: Router
  ) {
    this.shopForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      description: ['', [Validators.minLength(3)]]
    });
  }

  onSubmit() {
    if (this.shopForm.valid) {
      this.isSubmitting = true;
      this.shopService.createShop(this.shopForm.value as any).subscribe({
        next: (response) => {
          localStorage.setItem('hasShop', 'true');
          this.router.navigate(['/dashboard']);
        },
        error: (err) => {
          this.isSubmitting = false;
          this.error$.next('Error creating shop');
        }
      });
    }
  }
}

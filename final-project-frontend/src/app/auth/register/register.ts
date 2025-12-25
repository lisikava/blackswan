import { Component } from '@angular/core';
import { Auth } from '../../services/auth';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import {takeUntilDestroyed} from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MatButtonModule, MatDividerModule, MatIconModule, 
    MatFormFieldModule, MatInputModule, MatSelectModule, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.css',
})

export class Register {
  registerForm: FormGroup;
  message: string | null = null;
  error$: Subject<string> = new Subject<string>();

  constructor(
    private fb: FormBuilder,
    private auth: Auth,
    private router: Router
  ) {
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      username: ['', Validators.required],
      role: ['CUSTOMER', Validators.required], 
    });
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      this.message = null;
      const registrationData: RegisterRequest = this.registerForm.value;
      // debugger;
      this.auth.register(registrationData).subscribe({
        next: () => {
          // this.message = 'Registration successful! You can now log in.';
          this.router.navigate(['/login']);
        },
        error: (err) => {
          // console.error('Registration failed: ', err);
          this.error$.next('Registration failed');
        },
      });
    }
  }
}

export interface RegisterRequest {
  email: string;
  password: string;
  username: string;
  role: 'ARTIST' | 'CUSTOMER'; 
}

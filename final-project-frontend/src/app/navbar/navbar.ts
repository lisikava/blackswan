import { Component, OnInit, inject } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterLinkActive, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Auth } from '../services/auth';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MatToolbarModule, MatButtonModule, MatIconModule, RouterLink, RouterLinkActive, MatFormFieldModule, MatInputModule, FormsModule],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar  {
  query?: string;
  title = 'Black Swan';
  // isLoggedIn = false;
  constructor(protected auth: Auth, private router: Router) {}

  // ngOnInit() {
  //   // this.isLoggedIn = this.auth.isLoggedIn();
  // }

  logOut() {
    this.auth.logout();
    this.router.navigate(['/']);
  }

  search() {
    if (!this.query?.trim()) return;
    this.router.navigate(['/search'], { queryParams: { tag: this.query.trim() } });
  }
}

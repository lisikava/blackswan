import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  private apiUrl = "http://localhost:8080/auth";
  isLoggedIn = signal<boolean>(false);

  constructor(private http: HttpClient) {}

  register(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, data, {responseType: 'text'});
  }

  
  login(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, data)
      .pipe(
        tap((response: any) => {
          localStorage.setItem("token", response.token);
          this.isLoggedIn.set(true);
        })
      );
  }

  getToken(): string | null {
    return localStorage.getItem("token");
  }

  updateToken(newToken: string): void {
    localStorage.setItem("token", newToken);
  }

  getRole(): string | null {
    const token = this.getToken();
    if (!token) return null;
    const payload = JSON.parse(atob(token.split('.')[1]));
    return payload.role; 
  }

  // isLoggedIn(): boolean {
  //   return !!localStorage.getItem("token");
  // }

  logout(): void {
    localStorage.removeItem("token");
    this.isLoggedIn.set(false);
  }
}

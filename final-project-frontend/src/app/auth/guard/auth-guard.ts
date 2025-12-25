import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { Auth } from '../../services/auth';

export const authGuard: CanActivateFn = (route, state) => {
  // return true;
  const authService = inject(Auth);
  const router = inject(Router);
  const role = authService.getRole();
  if (role === 'ARTIST') {
    return true; 
  }
  if (role === 'CUSTOMER') {
    return router.parseUrl('/');
  }
  return router.parseUrl('/login');
};

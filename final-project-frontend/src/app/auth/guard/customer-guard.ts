import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { Auth } from '../../services/auth';

export const customerGuard: CanActivateFn = (route, state) => {
  const authService = inject(Auth);
  const router = inject(Router);
  const role = authService.getRole();
  if (role === 'CUSTOMER') {
    return true; 
  }
  if (role === 'ARTIST') {
    return router.parseUrl('/');
  }
  return router.parseUrl('/login');
};

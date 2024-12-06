
import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { Observable, of } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  return authService.isAuthenticated().pipe(
    map(isAuth => {
      if (isAuth) {
        return true;
      } else {
        router.navigate(['/register']);
        return false;
      }
    }),
    catchError(error => {
      if (error.status === 403) {
        router.navigate(['/banned']);
      } else {
        router.navigate(['/register']);
      }
      return of(false);
    })
  );
};
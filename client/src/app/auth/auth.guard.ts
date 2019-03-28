import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) { }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): any {
    let url: string = state.url;
    return this.verifyLogin();
  }

  verifyLogin(): boolean {
    if (!this.isLoggedIn()) {
      this.router.navigate(['/login']);
      return false;
    } else if (this.isLoggedIn()) {
      return true;
    }
  }

  public isLoggedIn() {
    return localStorage.getItem('isLoggedIn') == 'true';
  }
}

import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

    constructor(private router: Router) {
    }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (localStorage.getItem('roles').includes('Admin')) {
      return true;
    }

    this.router.navigate(['dashboard']);
    return false;
  }
}

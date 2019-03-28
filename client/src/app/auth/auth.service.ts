import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private router: Router, private toastr: ToastrService) { }

  logout() {
    localStorage.setItem('isLoggedIn', 'false');
    this.showSuccess();
    this.router.navigate(['/login'])
  }

  private showSuccess() {
    this.toastr.success('Successfully logged out!', 'Success')
  }
}

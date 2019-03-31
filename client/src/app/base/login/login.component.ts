import {Component, OnInit} from '@angular/core';
import {CreateUserDTO} from '../shared/create-user-dto.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';
import {AuthService} from '../../auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css', '../register/register.component.css']
})
export class LoginComponent implements OnInit {

  dto = new CreateUserDTO();
  loginForm: FormGroup;

  constructor(private router: Router, private authService: AuthService, private toastr: ToastrService) {
  }

  ngOnInit() {
    this.loginForm = new FormGroup({
      'username': new FormControl(this.dto.username, [Validators.required]),
      'password': new FormControl(this.dto.password, [Validators.required])
    });
    if (this.authService.isAuthenticated()) {
      this.router.navigate(['dashboard']);
    }
  }

  get username() {
    return this.loginForm.get('username');
  }

  get password() {
    return this.loginForm.get('password');
  }

  onSubmit() {
    this.authService.login(this.loginForm.value).subscribe(
      () => {
        this.showSuccess();
        this.router.navigate(['dashboard']);
      }, () => {
        this.showFailure();
      }
    )
  }

  private showSuccess() {
    this.toastr.success('Successfully logged in!', 'Success')
  }

  private showFailure() {
    this.toastr.error('Username or password are invalid.', 'Failure');
  }
}

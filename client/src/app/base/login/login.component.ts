import {Component, OnInit} from '@angular/core';
import {CreateUserDto} from '../shared/create-user-dto.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../../auth/auth.service';
import {CookieService} from 'ngx-cookie-service';
import {SharedService} from '../../shared/shared.service';
import {Constants} from '../../shared/constants';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css', '../register/register.component.css']
})
export class LoginComponent implements OnInit {

  dto = new CreateUserDto();
  loginForm: FormGroup;

  constructor(private router: Router, private authService: AuthService, private cookieService: CookieService,
              private sharedService: SharedService) {
  }

  ngOnInit() {
    this.loginForm = new FormGroup({
      'username': new FormControl(this.dto.username, [Validators.required]),
      'password': new FormControl(this.dto.password, [Validators.required])
    });
  }

  get username() {
    return this.loginForm.get('username');
  }

  get password() {
    return this.loginForm.get('password');
  }

  onSubmit(loginForm: FormGroup) {
    this.authService.login(loginForm.value).subscribe(
      () => {
        this.sharedService.showSuccessToastr(Constants.LOGIN);
        this.authService.getAuth().subscribe(
          (auth) => {
            this.authService.setAuthentication(auth);
          }
        );
        this.router.navigate(['']);
      }, () => {
        this.cookieService.deleteAll();
        this.sharedService.showFailureToastr(Constants.WRONG_CREDENTIALS);
      }
    )
  }
}

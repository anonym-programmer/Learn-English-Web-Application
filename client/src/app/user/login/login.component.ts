import { Component, OnInit } from '@angular/core';
import {CreateUserDTO} from '../shared/create-user-dto.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../shared/user.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css', '../register/register.component.css']
})
export class LoginComponent implements OnInit {

  dto = new CreateUserDTO();
  loginForm: FormGroup;

  constructor(private service: UserService,
              private toastr: ToastrService) { }

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

  onSubmit() {
    this.service.login(this.loginForm.value).subscribe(
      success => {
        this.showSuccess();
      }, error => {
        this.showFailure();
      }
    );
  }

  private showSuccess() {
    this.toastr.success('Successfully logged in!', 'Success')
  }

  private showFailure() {
    this.toastr.error('Username or password are invalid.', 'Failure');
  }
}

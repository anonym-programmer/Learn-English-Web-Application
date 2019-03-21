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
  dtoError = new CreateUserDTO();
  loginForm: FormGroup;

  constructor(private service: UserService, private toastr: ToastrService) { }

  ngOnInit() {
    this.loginForm = new FormGroup({
      'login': new FormControl(this.dto.login, [Validators.required]),
      'password': new FormControl(this.dto.password, [Validators.required])
    })
  }

  get login() {
    return this.loginForm.get('login');
  }

  get password() {
    return this.loginForm.get('password');
  }
}

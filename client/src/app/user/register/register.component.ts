import { Component, OnInit } from '@angular/core';
import { UserService } from '../shared/user.service';
import { CreateUserDTO } from '../shared/create-user-dto.model';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  dto = new CreateUserDTO();
  dtoError = new CreateUserDTO();
  registerForm: FormGroup;

  constructor(private service: UserService) { }

  ngOnInit() {
    this.registerForm = new FormGroup({
      'login': new FormControl(this.dto.login, [Validators.required]),
      'password': new FormControl(this.dto.password, [Validators.required]),
      'confirmedPassword': new FormControl(this.dto.confirmedPassword, [Validators.required])
    });
  }

  get login() { return this.registerForm.get('login'); }

  get password() { return this.registerForm.get('password'); }

  get confirmedPassword() { return this.registerForm.get('confirmedPassword'); }

  onSubmit() {
    this.service.create(this.registerForm.value).subscribe(
      data => {
        console.log(this.registerForm.value);
      },
      error => {
        this.dtoError = error.error;

        if (this.dtoError.login != null) {
          this.registerForm.controls['login'].reset();
        }

        if (this.dtoError.password != null) {
          this.registerForm.controls['password'].reset();
        }

        if (this.dtoError.confirmedPassword != null) {
          this.registerForm.controls['confirmedPassword'].reset();
        }

        console.log(this.dtoError);
      });
  }
}

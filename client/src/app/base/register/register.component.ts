import {Component, OnInit} from '@angular/core';
import {BaseService} from '../shared/base.service';
import {CreateUserDto} from '../shared/create-user-dto.model';
import {FormGroup, FormControl, Validators, AbstractControl} from '@angular/forms';
import {SharedService} from '../../shared/shared.service';
import {Constants} from '../../shared/constants';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  dto = new CreateUserDto();
  dtoError = new CreateUserDto();
  registerForm: FormGroup;

  constructor(private service: BaseService, private sharedService: SharedService) {
  }

  ngOnInit() {
    this.registerForm = new FormGroup({
      'username': new FormControl(this.dto.username, [Validators.required]),
      'email': new FormControl(this.dto.email, [Validators.required]),
      'password': new FormControl(this.dto.password, [Validators.required]),
      'confirmedPassword': new FormControl(this.dto.confirmedPassword, [Validators.required])
    });
  }

  get username() {
    return this.registerForm.get('username');
  }

  get email() {
    return this.registerForm.get('email');
  }

  get password() {
    return this.registerForm.get('password');
  }

  get confirmedPassword() {
    return this.registerForm.get('confirmedPassword');
  }

  onSubmit(registerForm: FormGroup) {
    this.service.register(registerForm.value).subscribe(
      () => {
        this.sharedService.showInfoAlert(
          Constants.ACCOUNT_CONFIRMATION_TITLE,
          Constants.ACCOUNT_CONFIRMATION_MSG
        );
        let control: AbstractControl = null;
        this.registerForm.reset();
        this.registerForm.markAsUntouched();
        Object.keys(this.registerForm.controls).forEach((name) => {
          control = this.registerForm.controls[name];
          control.setErrors(null);
        });
      },
      error => {
        this.sharedService.showFailureToastr(Constants.INVALID_FIELDS);
        this.dtoError = error.error;

        if (this.dtoError.username != null) {
          this.registerForm.controls['username'].reset();
        }

        if (this.dtoError.email != null) {
          this.registerForm.controls['email'].reset();
        }

        if (this.dtoError.password != null) {
          this.registerForm.controls['password'].reset();
        }

        if (this.dtoError.confirmedPassword != null) {
          this.registerForm.controls['confirmedPassword'].reset();
        }
      }
    );
  }
}

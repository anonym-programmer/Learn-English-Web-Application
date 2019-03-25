import {Component, OnInit} from '@angular/core';
import {UserService} from '../shared/user.service';
import {CreateUserDTO} from '../shared/create-user-dto.model';
import {FormGroup, FormControl, Validators, AbstractControl} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
import swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  dto = new CreateUserDTO();
  dtoError = new CreateUserDTO();
  registerForm: FormGroup;

  constructor(private service: UserService,
              private toastr: ToastrService) { }

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

  onSubmit() {
    this.service.register(this.registerForm.value).subscribe(
      data => {
        this.showInfo();
        let control: AbstractControl = null;
        this.registerForm.reset();
        this.registerForm.markAsUntouched();
        Object.keys(this.registerForm.controls).forEach((name) => {
          control = this.registerForm.controls[name];
          control.setErrors(null);
        });
      },
      error => {
        this.showFailure();
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

  private showInfo() {
    swal(
      'Account confirmation',
      'Confirmation token has been sent to your email.' + '<br>' +
               'U\'ve got 15 minutes from now, after that token will expire' + '<br>' +
               'and u will be not able to log into application!',
      'info'
    );
  }

  private showFailure() {
    this.toastr.error('Correct invalid fields.', 'Failure');
  }
}

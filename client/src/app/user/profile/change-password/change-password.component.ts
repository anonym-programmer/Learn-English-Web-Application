import {Component, OnInit} from '@angular/core';
import {ChangeUserPasswordDTO} from '../../shared/change-password-dto.model';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
import {UserService} from '../../shared/user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css',
              '../../../base/login/login.component.css',
              '../../../base/register/register.component.css']
})
export class ChangePasswordComponent implements OnInit {

  dto = new ChangeUserPasswordDTO();
  dtoError = new ChangeUserPasswordDTO();
  changePasswordForm: FormGroup;

  constructor(private userService: UserService, private toastr: ToastrService) {
  }

  ngOnInit() {
    this.changePasswordForm = new FormGroup({
      'password': new FormControl(this.dto.password, [Validators.required]),
      'confirmedPassword': new FormControl(this.dto.confirmedPassword, [Validators.required])
    });
  }

  get password() {
    return this.changePasswordForm.get('password');
  }

  get confirmedPassword() {
    return this.changePasswordForm.get('confirmedPassword');
  }

  changePassword() {
    this.userService.changePassword(this.changePasswordForm.value).subscribe(
      () => {
        this.showSuccess();
        let control: AbstractControl = null;
        this.changePasswordForm.reset();
        this.changePasswordForm.markAsUntouched();
        Object.keys(this.changePasswordForm.controls).forEach((name) => {
          control = this.changePasswordForm.controls[name];
          control.setErrors(null);
        });
      },
      error => {
        this.showFailure();
        this.dtoError = error.error;

        if (this.dtoError.password != null) {
          this.changePasswordForm.controls['password'].reset();
        }

        if (this.dtoError.confirmedPassword != null) {
          this.changePasswordForm.controls['confirmedPassword'].reset();
        }
      }
    );
  }

  private showSuccess() {
    this.toastr.success('Successfully changed password!', 'Success')
  }

  private showFailure() {
    this.toastr.error('Correct invalid fields.', 'Failure');
  }
}

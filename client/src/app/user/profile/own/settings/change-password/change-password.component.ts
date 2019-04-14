import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {ChangeUserPasswordDto} from '../../../../shared/change-password-dto.model';
import {UserService} from '../../../../shared/user.service';
import {SharedService} from "../../../../../shared/shared.service";
import {Constants} from "../../../../../shared/constants";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css',
    '../../../../../base/login/login.component.css',
    '../../../../../base/register/register.component.css'
  ]
})
export class ChangePasswordComponent implements OnInit {

  dto = new ChangeUserPasswordDto();
  dtoError = new ChangeUserPasswordDto();
  changePasswordForm: FormGroup;

  constructor(private userService: UserService, private sharedService: SharedService) {
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

  changePassword(changePasswordForm: FormGroup) {
    this.userService.changePassword(changePasswordForm.value).subscribe(
      () => {
        this.sharedService.showSuccessToastr(Constants.CHANGED_PASSWORD);
        let control: AbstractControl = null;
        this.changePasswordForm.reset();
        this.changePasswordForm.markAsUntouched();
        Object.keys(this.changePasswordForm.controls).forEach((name) => {
          control = this.changePasswordForm.controls[name];
          control.setErrors(null);
        });
      },
      error => {
        this.sharedService.showFailureToastr(Constants.INVALID_FIELDS);
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
}

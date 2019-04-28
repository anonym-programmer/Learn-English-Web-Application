import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {ChangeUserEmailDto} from '../../../../shared/change-email-dto.model';
import {UserService} from '../../../../shared/user.service';
import {SharedService} from '../../../../../shared/shared.service';
import {Constants} from '../../../../../shared/constants';

@Component({
  selector: 'app-change-email',
  templateUrl: './change-email.component.html',
  styleUrls: ['./change-email.component.css']
})
export class ChangeEmailComponent implements OnInit {

  dto = new ChangeUserEmailDto();
  dtoError = new ChangeUserEmailDto();
  changeEmailForm: FormGroup;

  constructor(private userService: UserService, private sharedService: SharedService) {
  }

  ngOnInit() {
    this.changeEmailForm = new FormGroup({
      'email': new FormControl(this.dto.email, [Validators.required]),
      'confirmedEmail': new FormControl(this.dto.confirmedEmail, [Validators.required])
    });
  }

  get email() {
    return this.changeEmailForm.get('email');
  }

  get confirmedEmail() {
    return this.changeEmailForm.get('confirmedEmail');
  }

  changeEmail(changeEmailForm: FormGroup) {
    this.userService.changeEmail(changeEmailForm.value).subscribe(
      () => {
        this.sharedService.showSuccessToastr(Constants.CHANGED_EMAIL);
        let control: AbstractControl = null;
        this.changeEmailForm.reset();
        this.changeEmailForm.markAsUntouched();
        Object.keys(this.changeEmailForm.controls).forEach(name => {
          control = this.changeEmailForm.controls[name];
          control.setErrors(null);
        });
      },
      error => {
        this.sharedService.showFailureToastr(Constants.INVALID_FIELDS);
        this.dtoError = error.error;

        if (this.dtoError.email != null) {
          this.changeEmailForm.controls['email'].reset();
        }

        if (this.dtoError.confirmedEmail != null) {
          this.changeEmailForm.controls['confirmedEmail'].reset();
        }
      }
    );
  }
}

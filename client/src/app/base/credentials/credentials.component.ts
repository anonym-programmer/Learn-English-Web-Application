import {Component, OnInit} from '@angular/core';
import {BaseService} from '../shared/base.service';
import {CreateUserDto} from '../shared/create-user-dto.model';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {SharedService} from '../../shared/shared.service';
import {Constants} from '../../shared/constants';

@Component({
  selector: 'app-credentials',
  templateUrl: './credentials.component.html',
  styleUrls: ['./credentials.component.css', '../register/register.component.css']
})
export class CredentialsComponent implements OnInit {

  dto = new CreateUserDto();
  dtoError = new CreateUserDto();
  credentialForm: FormGroup;

  constructor(private baseService: BaseService, private sharedService: SharedService) {
  }

  ngOnInit() {
    this.credentialForm = new FormGroup({
      'email': new FormControl(this.dto.email, [Validators.required]),
    })
  }

  get email() {
    return this.credentialForm.get('email');
  }

  onSubmit(credentialForm: FormGroup) {
    this.baseService.forgotCredentials(credentialForm.value).subscribe(
      () => {
        this.sharedService.showInfoAlert(
          Constants.FORGOT_CREDENTIALS_TITLE,
          Constants.FORGOT_CREDENTIALS_MSG
        );
        let control: AbstractControl = null;
        this.credentialForm.reset();
        this.credentialForm.markAsUntouched();
        Object.keys(this.credentialForm.controls).forEach((name) => {
          control = this.credentialForm.controls[name];
          control.setErrors(null);
        });
      },
      error => {
        this.sharedService.showFailureToastr(Constants.INVALID_FIELD);
        this.dtoError = error.error;

        if (this.dtoError.email != null) {
          this.credentialForm.controls['email'].reset();
        }
      }
    );
  }
}

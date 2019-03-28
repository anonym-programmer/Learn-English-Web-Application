import {Component, OnInit} from '@angular/core';
import {UserService} from '../shared/user.service';
import swal from 'sweetalert2';
import {CreateUserDTO} from '../shared/create-user-dto.model';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-credentials',
  templateUrl: './credentials.component.html',
  styleUrls: ['./credentials.component.css', '../register/register.component.css']
})
export class CredentialsComponent implements OnInit {

  dto = new CreateUserDTO();
  dtoError = new CreateUserDTO();
  credentialForm: FormGroup;

  constructor(private service: UserService,
              private toastr: ToastrService) { }

  ngOnInit() {
    this.credentialForm = new FormGroup({
      'email': new FormControl(this.dto.email, [Validators.required]),
    })
  }

  get email() {
    return this.credentialForm.get('email');
  }

  onSubmit() {
    this.service.forgotCredentials(this.credentialForm.value).subscribe(
      () => {
        this.showInfo();
        let control: AbstractControl = null;
        this.credentialForm.reset();
        this.credentialForm.markAsUntouched();
        Object.keys(this.credentialForm.controls).forEach((name) => {
          control = this.credentialForm.controls[name];
          control.setErrors(null);
        });
      },
      error => {
        this.showFailure();
        this.dtoError = error.error;

        if (this.dtoError.email != null) {
          this.credentialForm.controls['email'].reset();
        }
      }
    );
  }

  private showInfo() {
    swal(
      'Forgot credentials',
      'Reset token has been sent to your email.' + '<br>' +
      'U\'ve got 15 minutes from now, after that token will expire' + '<br>' +
      'and u will be not able to reset your password!',
      'info'
    );
  }

  private showFailure() {
    this.toastr.error('Correct invalid field.', 'Failure');
  }
}

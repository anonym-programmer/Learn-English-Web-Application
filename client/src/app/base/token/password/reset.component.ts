import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {BaseService} from '../../shared/base.service';
import {CreateUserDto} from '../../shared/create-user-dto.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {SharedService} from '../../../shared/shared.service';
import {Constants} from '../../../shared/constants';

@Component({
  selector: 'app-reset',
  templateUrl: './reset.component.html',
  styleUrls: ['./reset.component.css', '../../register/register.component.css']
})
export class ResetComponent implements OnInit {

  dto = new CreateUserDto();
  dtoError = new CreateUserDto();
  resetForm: FormGroup;

  constructor(private router: Router, private route: ActivatedRoute, private service: BaseService,
              private sharedService: SharedService) {
  }

  ngOnInit() {
    this.resetForm = new FormGroup({
      'password': new FormControl(this.dto.password, [Validators.required]),
      'confirmedPassword': new FormControl(this.dto.confirmedPassword, [Validators.required])
    });
    this.route.queryParams.subscribe(queryParams => {
      const token = queryParams['token'];
      this.service.checkToken(token).subscribe(() => {
        }, () => {
          this.router.navigate(['/']);
          this.sharedService.showErrorAlert(
            Constants.FAILURE_TITLE,
            Constants.TOKEN_CONFIRMATION_FAILURE_MSG
          );
        }
      );
    });
  }

  get password() {
    return this.resetForm.get('password');
  }

  get confirmedPassword() {
    return this.resetForm.get('confirmedPassword');
  }

  onSubmit(resetForm: FormGroup) {
    this.route.queryParams.subscribe(queryParams => {
      const token = queryParams['token'];
      this.service.resetPassword(token, resetForm.value).subscribe(
        () => {
          this.sharedService.showSuccessAlert(
            Constants.TOKEN_CONFIRMATION_SUCCESS_TITLE,
            Constants.CHANGED_PASSWORD
          );
          this.router.navigate(['/']);
        }, error => {
          this.sharedService.showFailureToastr(
            Constants.INVALID_FIELDS
          );
          this.dtoError = error.error;

          if (this.dtoError.password != null) {
            this.resetForm.controls['password'].reset();
          }

          if (this.dtoError.confirmedPassword != null) {
            this.resetForm.controls['confirmedPassword'].reset();
          }
        }
      );
    });
  }
}

import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../shared/user.service';
import {CreateUserDTO} from '../../shared/create-user-dto.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
import swal from 'sweetalert2';

@Component({
  selector: 'app-reset',
  templateUrl: './reset.component.html',
  styleUrls: ['./reset.component.css', '../../register/register.component.css']
})
export class ResetComponent implements OnInit {

  dto = new CreateUserDTO();
  dtoError = new CreateUserDTO();
  resetForm: FormGroup;

  constructor(private service: UserService,
              private toastr: ToastrService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.resetForm = new FormGroup({
      'password': new FormControl(this.dto.password, [Validators.required]),
      'confirmedPassword': new FormControl(this.dto.confirmedPassword, [Validators.required])
    });
    const token = this.route.queryParams.subscribe(queryParams => {
      const token = queryParams['token'];
      this.service.checkToken(token).subscribe(
        data => {},
        error => {
          this.router.navigate(['/']);
          this.showErrorAlert();
        }
      );
    })
  }

  get password() {
    return this.resetForm.get('password');
  }

  get confirmedPassword() {
    return this.resetForm.get('confirmedPassword');
  }

  onSubmit() {
    this.route.queryParams.subscribe(queryParams => {
      const token = queryParams['token'];
      this.service.resetPassword(token, this.resetForm.value).subscribe(
        data => {
          this.showSuccessAlert();
          this.router.navigate(['/']);
        },
        error => {
          this.showFailureToastr();
          this.dtoError = error.error;

          if (this.dtoError.password != null) {
            this.resetForm.controls['password'].reset();
          }

          if (this.dtoError.confirmedPassword != null) {
            this.resetForm.controls['confirmedPassword'].reset();
          }
        }
      );
    })
  }

  private showFailureToastr() {
    this.toastr.error('Correct invalid fields.', 'Failure');
  }

  private showSuccessAlert() {
    swal(
      'Good job!',
      'You have successfully changed your password!',
      'success'
    );
  }

  private showErrorAlert() {
    swal(
      'Oops...',
      'Token doesn\'t exist.',
      'error'
    );
  }
}

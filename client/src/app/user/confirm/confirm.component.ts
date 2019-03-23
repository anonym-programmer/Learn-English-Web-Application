import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UserService} from '../shared/user.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-confirm',
  templateUrl: './confirm.component.html',
  styleUrls: ['./confirm.component.css']
})
export class ConfirmComponent implements OnInit {

  constructor(private service: UserService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe(queryParams => {
      const token = queryParams['token'];
      this.service.confirmAccount(token).subscribe(
        isCorrect => {
          this.showSuccess();
        },
        isNotCorrect => {
          this.showError();
        }
      )
    })
  }

  private showSuccess() {
    swal(
      'Good job!',
      'You have successfully confirmed your account!',
      'success'
    );
  }

  private showError() {
    swal(
      'Oops...',
      'Confirmation token doesn\'t exist.',
      'error'
    );
  }
}

import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../shared/user.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-confirm',
  templateUrl: './confirm.component.html',
  styleUrls: ['./confirm.component.css']
})
export class ConfirmComponent implements OnInit {

  constructor(private service: UserService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.queryParams.subscribe(queryParams => {
      const token = queryParams['token'];
      this.service.confirmAccount(token).subscribe(
        isCorrect => {
          this.showSuccessAlert();
        },
        isNotCorrect => {
          this.showErrorAlert();
        }
      );
      this.router.navigate(['/']);
    })
  }

  private showSuccessAlert() {
    swal(
      'Good job!',
      'You have successfully confirmed your account!',
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

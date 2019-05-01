import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {BaseService} from '../../shared/base.service';
import {SharedService} from '../../../shared/shared.service';
import {Constants} from '../../../shared/constants';

@Component({
  selector: 'app-confirm',
  templateUrl: './confirm.component.html',
  styleUrls: ['./confirm.component.css']
})
export class ConfirmComponent implements OnInit {

  constructor(private router: Router, private route: ActivatedRoute, private service: BaseService,
              private sharedService: SharedService) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe(queryParams => {
      const token = queryParams['token'];
      this.service.confirmAccount(token).subscribe(
        () => {
          this.sharedService.showSuccessAlert(
            Constants.TOKEN_CONFIRMATION_SUCCESS_TITLE,
            Constants.TOKEN_CONFIRMATION_SUCCESS_MSG
          );
        },
        () => {
          this.sharedService.showErrorAlert(
            Constants.FAILURE_TITLE,
            Constants.TOKEN_CONFIRMATION_FAILURE_MSG
          );
        }
      );
      this.router.navigate(['']);
    });
  }
}

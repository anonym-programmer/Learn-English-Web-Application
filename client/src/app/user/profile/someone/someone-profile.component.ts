import {Component, OnInit} from '@angular/core';
import {UserService} from '../../shared/user.service';
import {QuerySomeoneProfile} from '../../shared/query-someone-profile.model';
import {ActivatedRoute, Router} from '@angular/router';
import {SharedService} from "../../../shared/shared.service";
import {Constants} from "../../../shared/constants";

@Component({
  selector: 'app-someone-profile',
  templateUrl: './someone-profile.component.html',
  styleUrls: ['./someone-profile.component.css']
})
export class SomeoneProfileComponent implements OnInit {

  profile = new QuerySomeoneProfile();
  username: string;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private userService: UserService,
              private sharedService: SharedService) {
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(param => {
      this.username = param['username'];
    });

    this.userService.getInfoAboutSomeoneProfile(this.username).subscribe(profile => {
      this.profile = profile;
    }, () => {
      this.sharedService.showErrorAlert(
        Constants.FAILURE_TITLE,
        Constants.USER_DOESNT_EXIST
      );
      this.router.navigate(['forum']);
    });
  }
}

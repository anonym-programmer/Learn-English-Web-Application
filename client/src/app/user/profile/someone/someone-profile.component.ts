import {Component, OnInit} from '@angular/core';
import {UserService} from '../../shared/user.service';
import {QuerySomeoneProfile} from '../../shared/query-someone-profile.model';
import {ActivatedRoute, Router} from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-someone-profile',
  templateUrl: './someone-profile.component.html',
  styleUrls: ['./someone-profile.component.css',
              '../own/profile.component.css'
  ]
})
export class SomeoneProfileComponent implements OnInit {

  profile = new QuerySomeoneProfile();
  username: string;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private userService: UserService) {
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(param => {
      this.username = param['username'];
      console.log(this.username);
    });

    this.userService.getInfoAboutSomeoneProfile(this.username).subscribe(profile => {
        this.profile = profile;
    }, () => {
        this.showErrorAlert();
        this.router.navigate(['forum']);
    });
  }

  private showErrorAlert() {
    swal(
      'Oops...',
      'User doesn\'t exist.',
      'error'
    );
  }
}

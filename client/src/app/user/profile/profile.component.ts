import {Component, OnInit} from '@angular/core';
import {UserService} from '../shared/user.service';
import {QueryProfile} from '../shared/query-profile.model';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  profile = new QueryProfile();

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getInfoAboutMyProfile().subscribe(profile => {
      this.profile = profile;
    })
  }
}

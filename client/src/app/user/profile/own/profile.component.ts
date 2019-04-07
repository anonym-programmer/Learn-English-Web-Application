import {Component, OnInit} from '@angular/core';
import {QueryOwnProfile} from '../../shared/query-own-profile.model';
import {UserService} from '../../shared/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  profile = new QueryOwnProfile();

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getInfoAboutMyProfile().subscribe(profile => {
      this.profile = profile;
    })
  }
}

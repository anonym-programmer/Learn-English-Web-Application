import {Component, OnInit} from '@angular/core';
import {UserService} from '../../../shared/user.service';
import {QueryOwnProfile} from '../../../shared/query-own-profile.model';


@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  profile = new QueryOwnProfile();

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getInfoAboutMyProfile().subscribe(profile => {
      this.profile = profile;
    })
  }
}


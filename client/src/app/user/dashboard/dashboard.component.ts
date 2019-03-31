import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../auth/auth.service';
import {UserService} from '../shared/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  msg: string;

  constructor(private authService: AuthService, private userService: UserService) {
  }

  ngOnInit() {
    this.userService.greetUser().subscribe(msg => {
      this.msg = msg;
    })
  }

  onLogout() {
    this.authService.logout();
  }
}

import {Component, OnInit} from '@angular/core';
import {AuthService} from '../auth/auth.service';
import {UserService} from '../user/shared/user.service';

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
    if (this.authService.isAuthenticated()) {
      this.userService.greetUser().subscribe(msg => {
        this.msg = msg;
      });
    } else {
      this.msg = 'Welcome anonymous!';
    }
  }
}

import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../auth/auth.service';
import {QueryAuth} from '../shared/query-auth.model';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  auth: QueryAuth;

  constructor(private authService: AuthService) {
  }

  ngOnInit() {
    this.authService.getAuth().subscribe(auth => {
      this.auth = auth;
    })
  }
}

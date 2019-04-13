import {Component, OnInit} from '@angular/core';
import {QueryUser} from "./shared/query-user.model";
import {AuthService} from "../auth/auth.service";
import {AdminService} from "./shared/admin.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  private page: number = 0;
  private users: Array<QueryUser>;
  private pages: Array<number>;

  constructor(private authService: AuthService, private adminService: AdminService, private toastr: ToastrService) {
  }

  setPage(i, event: any) {
    event.preventDefault();
    this.page = i;
    this.getUsers();
  }

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.adminService.getUsers(this.page).subscribe(data => {
      this.users = data['content'];
      this.pages = new Array(data['totalPages']);
    })
  }
}

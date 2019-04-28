import {Component, OnInit} from '@angular/core';
import {QueryUser} from './shared/query-user.model';
import {AdminService} from './shared/admin.service';
import {SharedService} from '../shared/shared.service';
import {Constants} from '../shared/constants';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  private page: number = 0;
  private users: Array<QueryUser>;
  private pages: Array<number>;

  constructor(private adminService: AdminService, private sharedService: SharedService) {
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
    });
  }

  deleteUser(id: string) {
    this.adminService.deleteUser(id).subscribe(
      () => {
        this.sharedService.showSuccessToastr(Constants.DELETE_USER);
        this.getUsers();
      },
      () => {
        this.sharedService.showFailureToastr(Constants.SOMETHING_WRONG);
      }
    )
  }
}

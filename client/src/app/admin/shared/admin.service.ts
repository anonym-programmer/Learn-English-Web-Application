import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  readonly getUsersUrl = '/api/admin-query/users';
  readonly deleteUserUrl = '/api/admin/user';

  constructor(private http: HttpClient) {
  }

  getUsers(page: number) {
    return this.http.get(this.getUsersUrl + `?page=${page}`);
  }

  deleteUser(id: string) {
    return this.http.delete(this.deleteUserUrl + `/${id}`);
  }
}

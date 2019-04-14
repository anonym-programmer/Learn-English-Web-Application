import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  readonly getUsersUrl = 'http://localhost:8080/api/admin-query/users';

  constructor(private http: HttpClient) {
  }

  getUsers(page: number) {
    return this.http.get(this.getUsersUrl + '?page=' + page, {withCredentials: true});
  }
}

import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FormGroup} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  readonly greetUserUrl = 'http://localhost:8080/api/user';
  readonly changePasswordUrl = 'http://localhost:8080/api/user/change-password';
  readonly changeEmailUrl = 'http://localhost:8080/api/user/change-email';

  constructor(private http: HttpClient) {
  }

  greetUser() {
    return this.http.get(this.greetUserUrl, {responseType: 'text', withCredentials: true});
  }

  changePassword(data: FormGroup) {
    return this.http.post(this.changePasswordUrl, data, {withCredentials: true});
  }

  changeEmail(data: FormGroup) {
    return this.http.post(this.changeEmailUrl, data, {withCredentials: true});
  }
}

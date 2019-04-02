import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FormGroup} from '@angular/forms';
import {Observable} from 'rxjs';
import {QueryAuth} from './query-auth.model';
import {QueryProfile} from './query-profile.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  readonly greetUserUrl = 'http://localhost:8080/api/user';
  readonly infoAboutProfileUrl = 'http://localhost:8080/api/user/my-profile';
  readonly changePasswordUrl = 'http://localhost:8080/api/user/change-password';
  readonly changeEmailUrl = 'http://localhost:8080/api/user/change-email';

  constructor(private http: HttpClient) {
  }

  greetUser() {
    return this.http.get(this.greetUserUrl, {responseType: 'text', withCredentials: true});
  }

  getInfoAboutMyProfile(): Observable<QueryProfile> {
    return this.http.get<QueryProfile>(this.infoAboutProfileUrl, {withCredentials: true});
  }

  changePassword(data: FormGroup) {
    return this.http.post(this.changePasswordUrl, data, {withCredentials: true});
  }

  changeEmail(data: FormGroup) {
    return this.http.post(this.changeEmailUrl, data, {withCredentials: true});
  }
}

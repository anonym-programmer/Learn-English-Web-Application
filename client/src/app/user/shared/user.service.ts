import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FormGroup} from '@angular/forms';
import {Observable} from 'rxjs';
import {QueryOwnProfile} from './query-own-profile.model';
import {QuerySomeoneProfile} from './query-someone-profile.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  readonly greetUserUrl = 'http://localhost:8080/api/user';
  readonly infoAboutProfileUrl = 'http://localhost:8080/api/user/my-profile';
  readonly infoAboutSomeoneProfileUrl = 'http://localhost:8080/api/user/profile/';
  readonly changePasswordUrl = 'http://localhost:8080/api/user/change-password';
  readonly changeEmailUrl = 'http://localhost:8080/api/user/change-email';

  constructor(private http: HttpClient) {
  }

  greetUser() {
    return this.http.get(this.greetUserUrl, {responseType: 'text', withCredentials: true});
  }

  getInfoAboutMyProfile(): Observable<QueryOwnProfile> {
    return this.http.get<QueryOwnProfile>(this.infoAboutProfileUrl, {withCredentials: true});
  }

  getInfoAboutSomeoneProfile(username: string): Observable<QuerySomeoneProfile> {
    return this.http.get<QuerySomeoneProfile>(this.infoAboutSomeoneProfileUrl + `${username}`, {withCredentials: true});
  }

  changePassword(data: FormGroup) {
    return this.http.post(this.changePasswordUrl, data, {withCredentials: true});
  }

  changeEmail(data: FormGroup) {
    return this.http.post(this.changeEmailUrl, data, {withCredentials: true});
  }
}

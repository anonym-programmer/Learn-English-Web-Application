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

  readonly greetUserUrl = '/api/user';
  readonly infoAboutProfileUrl = '/api/user/my-profile';
  readonly infoAboutSomeoneProfileUrl = '/api/user/profile/';
  readonly changePasswordUrl = '/api/user/change-password';
  readonly changeEmailUrl = '/api/user/change-email';

  constructor(private http: HttpClient) {
  }

  greetUser() {
    return this.http.get(this.greetUserUrl, {responseType: 'text'});
  }

  getInfoAboutMyProfile(): Observable<QueryOwnProfile> {
    return this.http.get<QueryOwnProfile>(this.infoAboutProfileUrl);
  }

  getInfoAboutSomeoneProfile(username: string): Observable<QuerySomeoneProfile> {
    return this.http.get<QuerySomeoneProfile>(this.infoAboutSomeoneProfileUrl + `${username}`);
  }

  changePassword(data: FormGroup) {
    return this.http.post(this.changePasswordUrl, data);
  }

  changeEmail(data: FormGroup) {
    return this.http.post(this.changeEmailUrl, data);
  }
}

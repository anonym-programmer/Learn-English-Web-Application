import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  readonly greetUserUrl = 'http://localhost:8080/api/user';

  constructor(private http: HttpClient) {
  }

  greetUser() {
    return this.http.get(this.greetUserUrl, {responseType: 'text', withCredentials: true});
  }
}

import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {FormGroup} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  readonly createUrl = 'http://localhost:8080/api/user';
  readonly confirmUrl = 'http://localhost:8080/api/user/confirm-account';

  constructor(private http: HttpClient) {
  }

  create(data: FormGroup): Observable<FormGroup> {
    return this.http.post<FormGroup>(this.createUrl, data);
  }

  confirm(token: string): Observable<string> {
    return this.http.get<string>(this.confirmUrl + '?token=' + token);
  }
}

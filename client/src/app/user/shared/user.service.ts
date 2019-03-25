import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {FormGroup} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  readonly registerUrl = 'http://localhost:8080/api/base/register';
  readonly checkTokenUrl = 'http://localhost:8080/api/base/check-token';
  readonly confirmAccountUrl = 'http://localhost:8080/api/base/confirm-account';
  readonly forgotCredentialsUrl = 'http://localhost:8080/api/base/forgot-credentials';
  readonly resetPasswordUrl = 'http://localhost:8080/api/base/reset-password';

  constructor(private http: HttpClient) { }

  register(data: FormGroup): Observable<FormGroup> {
    return this.http.post<FormGroup>(this.registerUrl, data);
  }

  checkToken(token: string): Observable<string> {
    return this.http.get<string>(this.checkTokenUrl + '?token=' + token);
  }

  confirmAccount(token: string): Observable<string> {
    return this.http.get<string>(this.confirmAccountUrl + '?token=' + token);
  }

  forgotCredentials(data: FormGroup): Observable<FormGroup> {
    return this.http.post<FormGroup>(this.forgotCredentialsUrl, data);
  }

  resetPassword(token: string, data: FormGroup): Observable<FormGroup> {
    return this.http.post<FormGroup>(this.resetPasswordUrl + '?token=' + token, data);
  }
}

import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FormGroup} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class BaseService {

  readonly registerUrl = 'http://localhost:8080/api/base/register';
  readonly checkTokenUrl = 'http://localhost:8080/api/base/check-token';
  readonly confirmAccountUrl = 'http://localhost:8080/api/base/confirm-account';
  readonly forgotCredentialsUrl = 'http://localhost:8080/api/base/forgot-credentials';
  readonly resetPasswordUrl = 'http://localhost:8080/api/base/reset-password';

  constructor(private http: HttpClient) {
  }

  register(data: FormGroup) {
    return this.http.post(this.registerUrl, data);
  }

  checkToken(token: string) {
    return this.http.get(this.checkTokenUrl + '?token=' + token);
  }

  confirmAccount(token: string) {
    return this.http.get(this.confirmAccountUrl + '?token=' + token);
  }

  forgotCredentials(data: FormGroup) {
    return this.http.post(this.forgotCredentialsUrl, data);
  }

  resetPassword(token: string, data: FormGroup) {
    return this.http.post(this.resetPasswordUrl + '?token=' + token, data);
  }
}

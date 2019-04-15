import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FormGroup} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class BaseService {

  readonly registerUrl = '/api/base/register';
  readonly checkTokenUrl = '/api/base/check-token';
  readonly confirmAccountUrl = '/api/base/confirm-account';
  readonly forgotCredentialsUrl = '/api/base/forgot-credentials';
  readonly resetPasswordUrl = '/api/base/reset-password';

  constructor(private http: HttpClient) {
  }

  register(data: FormGroup) {
    return this.http.post(this.registerUrl, data);
  }

  checkToken(token: string) {
    return this.http.get(this.checkTokenUrl + `?token=${token}`);
  }

  confirmAccount(token: string) {
    return this.http.get(this.confirmAccountUrl + `?token=${token}`);
  }

  forgotCredentials(data: FormGroup) {
    return this.http.post(this.forgotCredentialsUrl, data);
  }

  resetPassword(token: string, data: FormGroup) {
    return this.http.post(this.resetPasswordUrl + `?token=${token}`, data);
  }
}

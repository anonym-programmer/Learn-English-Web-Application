import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {CookieService} from 'ngx-cookie-service';
import {Observable} from 'rxjs';
import {HttpClient, HttpParams} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {QueryAuth} from '../user/shared/query-auth.model';
import {SharedService} from '../shared/shared.service';
import {Constants} from '../shared/constants';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  readonly loginUrl = '/j_spring_security_check';
  readonly logoutUrl = '/j_spring_security_logout';
  readonly authUrl = '/api/auth';

  constructor(private http: HttpClient, private router: Router, private cookieService: CookieService,
              private sharedService: SharedService) {
  }

  public isAuthenticated(): boolean {
    return this.cookieService.get('XSRF-TOKEN') != '' && localStorage.getItem('isAuthenticated') == 'true';
  }

  public isRoleAdmin(): boolean {
    return localStorage.getItem('roles').includes('Admin');
  }

  public login(credentials: { username: string, password: string }): Observable<boolean> {
    const body = new HttpParams()
      .set('username', credentials.username)
      .set('password', credentials.password);

    return this.http
      .post(this.loginUrl, body)
      .pipe(map(() => true));
  }

  public setAuthentication(auth: QueryAuth) {
    localStorage.setItem('username', auth.username);
    localStorage.setItem('roles', auth.roles);
    localStorage.setItem('isAuthenticated', auth.isAuthenticated)
  }

  public logout() {
    return this.http.post(this.logoutUrl, null).subscribe(
      () => {
        this.cookieService.deleteAll();
        localStorage.clear();
        this.sharedService.showSuccessToastr(Constants.LOGOUT);
        this.router.navigate(['login']);
      }
    );
  }

  public getAuth(): Observable<QueryAuth> {
    return this.http.get<QueryAuth>(this.authUrl);
  }
}

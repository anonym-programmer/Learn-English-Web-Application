import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {CookieService} from 'ngx-cookie-service';
import {Observable} from 'rxjs';
import {HttpClient, HttpParams} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,
              private router: Router,
              private cookieService: CookieService,
              private toastr: ToastrService) { }

  readonly loginUrl= 'http://localhost:8080/j_spring_security_check';
  readonly logoutUrl= 'http://localhost:8080/j_spring_security_logout';

  public isAuthenticated(): boolean {
    return this.cookieService.get('XSRF-TOKEN') != '';
  }

  login(credentials: { username: string, password: string }): Observable<boolean> {
    const body = new HttpParams()
      .set('username', credentials.username)
      .set('password', credentials.password);

    return this.http
      .post(this.loginUrl, body, {withCredentials: true})
      .pipe(map(() => true));
  }

  logout() {
    return this.http.post(this.logoutUrl, null).subscribe(
      () => {
        this.cookieService.delete('XSRF-TOKEN');
        this.showSuccess();
        this.router.navigate(['login']);
      }
    );
  }

  private showSuccess() {
    this.toastr.success('Successfully logged out!', 'Success')
  }
}

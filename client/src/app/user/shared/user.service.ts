import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FormGroup } from '@angular/forms';

@Injectable({
    providedIn: 'root'
})
export class UserService {

    readonly baseUrl = 'http://localhost:8080/api/user';

    constructor(private http: HttpClient) { }

    create(data: FormGroup): Observable<FormGroup> {
      return this.http.post<FormGroup>(this.baseUrl, data);
    }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CreateUserDTO } from './create-user-dto.model';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class UserService {

    readonly baseUrl = 'http://localhost:8080/api/user';

    constructor(private http: HttpClient) { }

    create(dto: CreateUserDTO): Observable<CreateUserDTO> {
        return this.http.post<CreateUserDTO>(this.baseUrl, dto);
    }
}

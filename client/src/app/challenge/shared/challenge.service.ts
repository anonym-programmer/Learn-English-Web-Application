import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {FormGroup} from "@angular/forms";
import {Observable} from "rxjs";
import {QuestionQuery} from "./question-query.model";

@Injectable({
  providedIn: 'root'
})
export class ChallengeService {

  readonly makeUrl = '/api/challenge/make';

  constructor(private http: HttpClient) {
  }

  make(data: FormGroup): Observable<Array<QuestionQuery>> {
    return this.http.post(this.makeUrl, data);
  }
}

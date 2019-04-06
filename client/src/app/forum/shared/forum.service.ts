import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ForumService {

  readonly addPostUrl = 'http://localhost:8080/api/post';
  readonly getPostsUrl = 'http://localhost:8080/api/post-query';

  constructor(private http: HttpClient) {
  }

  addPost(data: FormData) {
    return this.http.post(this.addPostUrl, data, {withCredentials: true});
  }

  getPosts(page: number) {
    return this.http.get(this.getPostsUrl + '?page=' + page);
  }
}

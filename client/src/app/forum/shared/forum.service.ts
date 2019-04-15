import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {QueryPost} from './query-post.model';
import {QueryComment} from './query-comment.model';

@Injectable({
  providedIn: 'root'
})
export class ForumService {

  readonly addPostUrl = 'http://localhost:8080/api/post';
  readonly addCommentUrl = 'http://localhost:8080/api/comment';
  readonly getPostsUrl = 'http://localhost:8080/api/post-query';
  readonly votePostUrl = 'http://localhost:8080/api/vote';
  readonly getCommentsUrl = 'http://localhost:8080/api/comment-query';

  constructor(private http: HttpClient) {
  }

  addPost(data: FormData) {
    return this.http.post(this.addPostUrl, data);
  }

  addComment(text: string, postId: string) {
    return this.http.post(this.addCommentUrl, {text, postId});
  }

  getPosts(page: number) {
    return this.http.get(this.getPostsUrl + `?page=${page}`);
  }

  getPost(id: string): Observable<QueryPost> {
    return this.http.get<QueryPost>(this.getPostsUrl + `/${id}`);
  }

  votePost(postId: string, type: string) {
    return this.http.post(this.votePostUrl, {postId, type});
  }

  getComments(postId: string): Observable<Array<QueryComment>> {
    return this.http.get<Array<QueryComment>>(this.getCommentsUrl + `/${postId}`);
  }
}

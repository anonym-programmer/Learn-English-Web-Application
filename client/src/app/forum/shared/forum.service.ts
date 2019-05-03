import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subject} from 'rxjs';
import {QueryPost} from './query-post.model';
import {QueryComment} from './query-comment.model';

@Injectable({
  providedIn: 'root'
})
export class ForumService {

  readonly addPostUrl = '/api/post';
  readonly addCommentUrl = '/api/comment';
  readonly getPostsUrl = '/api/post-query';
  readonly votePostUrl = '/api/vote';
  readonly getCommentsUrl = '/api/comment-query';
  readonly getUserForumProfileUrl = '/api/user-query/forum-profile';

  public postAdded_Observable = new Subject();

  constructor(private http: HttpClient) {
  }

  notifyPostAddition() {
    this.postAdded_Observable.next();
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

  getUserForumProfile(username: string) {
    return this.http.get(this.getUserForumProfileUrl + `/${username}`);
  }
}

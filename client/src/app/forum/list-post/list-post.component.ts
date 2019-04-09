import {Component, OnInit} from '@angular/core';
import {ForumService} from '../shared/forum.service';
import {QueryPost} from '../shared/query-post.model';
import {AuthService} from '../../auth/auth.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-list-post',
  templateUrl: './list-post.component.html',
  styleUrls: ['./list-post.component.css']
})
export class ListPostComponent implements OnInit {

  private page: number = 0;
  private posts: Array<QueryPost>;
  private pages: Array<number>;

  constructor(private authService: AuthService, private forumService: ForumService, private toastr: ToastrService) {
  }

  setPage(i, event: any) {
    event.preventDefault();
    this.page = i;
    this.getPosts();
  }

  ngOnInit() {
    this.getPosts();
  }

  getPosts() {
    this.forumService.getPosts(this.page).subscribe(data => {
      this.posts = data['content'];
      this.pages = new Array(data['totalPages']);
    })
  }

  votePost(id: string, type: string) {
    this.forumService.votePost(id, type).subscribe(
      () => {
        this.showSuccess();
        this.getPosts();
      }, error => {
        this.showFailure(error.error['type']);
      }
    )
  }

  private showSuccess() {
    this.toastr.success('Successfully voted post!', 'Success');
  }

  private showFailure(msg: string) {
    this.toastr.error(`${msg}.`, 'Failure');
  }
}

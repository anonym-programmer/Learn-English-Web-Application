import {Component, OnInit} from '@angular/core';
import {ForumService} from '../shared/forum.service';
import {QueryPost} from '../shared/query-post.model';

@Component({
  selector: 'app-list-post',
  templateUrl: './list-post.component.html',
  styleUrls: ['./list-post.component.css']
})
export class ListPostComponent implements OnInit {

  private page: number = 0;
  private posts: Array<QueryPost>;
  private pages: Array<number>;

  constructor(private forumService: ForumService) {
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
}

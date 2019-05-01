import {Component, OnInit} from '@angular/core';
import {QueryPost} from '../shared/query-post.model';
import {ForumService} from '../shared/forum.service';
import {ActivatedRoute, Router} from '@angular/router';
import {QueryComment} from '../shared/query-comment.model';
import {AuthService} from '../../auth/auth.service';
import {SharedService} from '../../shared/shared.service';
import {Constants} from '../../shared/constants';
import {ShowForumUserProfileComponent} from "../show-forum-user-profile/show-forum-user-profile.component";
import {MatDialog} from "@angular/material";

@Component({
  selector: 'app-show-post',
  templateUrl: './show-post.component.html',
  styleUrls: ['./show-post.component.css']
})
export class ShowPostComponent implements OnInit {

  private post = new QueryPost();
  private comments = new Array<QueryComment>();
  id: string;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private forumService: ForumService,
              private authService: AuthService, private sharedService: SharedService, public dialog: MatDialog) {
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(param => {
      this.id = param['id'];
    });

    this.getPost();
  }

  getPost() {
    this.forumService.getPost(this.id).subscribe(post => {
      this.post = post;
      this.getComments();
    }, () => {
      this.sharedService.showErrorAlert(
        Constants.FAILURE_TITLE,
        Constants.POST_DOESNT_EXIST
      );
      this.router.navigate(['forum']);
    });
  }

  getComments() {
    this.forumService.getComments(this.id).subscribe(data => {
      this.comments = data;
    });
  }

  showForumUserProfile(username: string) {
    this.forumService.getUserForumProfile(username).subscribe(data => {
      this.dialog.open(ShowForumUserProfileComponent, {
        width: '20%',
        height: '38%',
        data: data
      });
    });
  }
}

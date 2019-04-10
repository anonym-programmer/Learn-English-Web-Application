import {Component, OnInit} from '@angular/core';
import {QueryPost} from '../shared/query-post.model';
import {ForumService} from '../shared/forum.service';
import {ActivatedRoute, Router} from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-show-post',
  templateUrl: './show-post.component.html',
  styleUrls: ['./show-post.component.css']
})
export class ShowPostComponent implements OnInit {

  private post = new QueryPost();
  id: string;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private forumService: ForumService) {
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
    }, () => {
      this.showErrorAlert();
      this.router.navigate(['forum']);
    })
  }

  private showErrorAlert() {
    swal(
      'Oops...',
      'Post doesn\'t exist.',
      'error'
    );
  }
}

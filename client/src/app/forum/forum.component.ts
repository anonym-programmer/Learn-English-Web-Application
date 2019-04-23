import {Component} from '@angular/core';
import {AuthService} from '../auth/auth.service';
import {MatDialog} from "@angular/material";
import {AddPostComponent} from "./add-post/add-post.component";

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.css']
})
export class ForumComponent {

  constructor(private authService: AuthService, public dialog: MatDialog) {
  }

  addPost() {
    this.dialog.open(AddPostComponent);
  }
}

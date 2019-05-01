import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {UserForumProfileQuery} from "../shared/user-forum-profile-query.model";

@Component({
  selector: 'app-show-forum-user-profile',
  templateUrl: './show-forum-user-profile.component.html',
  styleUrls: ['./show-forum-user-profile.component.css']
})
export class ShowForumUserProfileComponent {

  constructor(public dialogRef: MatDialogRef<ShowForumUserProfileComponent>,
              @Inject(MAT_DIALOG_DATA) public profile: UserForumProfileQuery) {
  }

  onNoClick() {
    this.dialogRef.close();
  }
}

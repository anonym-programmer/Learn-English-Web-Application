import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {UserChallengeProfileQuery} from "../shared/user-challenge-profile-query.model";

@Component({
  selector: 'app-show-rival-profile',
  templateUrl: './show-rival-profile.component.html',
  styleUrls: ['./show-rival-profile.component.css']
})
export class ShowRivalProfileComponent {

  constructor(public dialogRef: MatDialogRef<ShowRivalProfileComponent>,
              @Inject(MAT_DIALOG_DATA) public profile: UserChallengeProfileQuery) {
  }

  onNoClick() {
    this.dialogRef.close();
  }
}

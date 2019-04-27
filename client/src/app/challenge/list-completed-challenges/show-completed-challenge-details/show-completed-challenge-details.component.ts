import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {CompletedChallengeDetailsQuery} from "../../shared/completed-challenge-details-query.model";

@Component({
  selector: 'app-show-completed-challenge-details',
  templateUrl: './show-completed-challenge-details.component.html',
  styleUrls: ['./show-completed-challenge-details.component.css']
})
export class ShowCompletedChallengeDetailsComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ShowCompletedChallengeDetailsComponent>,
              @Inject(MAT_DIALOG_DATA) public details: CompletedChallengeDetailsQuery) {
  }

  ngOnInit() {

  }

  close() {
    this.dialogRef.close();
  }

  get username() {
    return this.details.username;
  }
}

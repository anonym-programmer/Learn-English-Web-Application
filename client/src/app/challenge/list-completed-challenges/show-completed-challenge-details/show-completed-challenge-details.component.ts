import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material";
import {CompletedChallengeDetailsQuery} from "../../shared/completed-challenge-details-query.model";
import {ChallengeService} from "../../shared/challenge.service";
import {ShowCorrectAnswersChallengeDetailsComponent} from "./show-correct-answers-challenge/show-correct-answers-challenge-details.component";

@Component({
  selector: 'app-show-completed-challenge-details',
  templateUrl: './show-completed-challenge-details.component.html',
  styleUrls: ['./show-completed-challenge-details.component.css']
})
export class ShowCompletedChallengeDetailsComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ShowCompletedChallengeDetailsComponent>,
              @Inject(MAT_DIALOG_DATA) public details: CompletedChallengeDetailsQuery,
              private challengeService: ChallengeService, private dialog: MatDialog) {
  }

  ngOnInit() {

  }

  viewCorrectAnswers(challengeId: string) {
    this.challengeService.getCompletedChallengeDetailsCorrectAnswersById(challengeId).subscribe(data => {
      this.dialog.open(ShowCorrectAnswersChallengeDetailsComponent, {
        width: '40%',
        height: '85%',
        data: data
      })
    });
  }

  close() {
    this.dialogRef.close();
  }

  get username() {
    return this.details.username;
  }
}

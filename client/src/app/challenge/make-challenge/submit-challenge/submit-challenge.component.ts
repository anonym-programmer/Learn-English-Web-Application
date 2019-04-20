import {Component, Inject, OnInit} from '@angular/core';
import {QuestionQuery} from "../../shared/question-query.model";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";

@Component({
  selector: 'app-submit-challenge',
  templateUrl: './submit-challenge.component.html',
  styleUrls: ['./submit-challenge.component.css']
})
export class SubmitChallengeComponent implements OnInit {

  questions: Array<QuestionQuery>;

  constructor(public dialogRef: MatDialogRef<SubmitChallengeComponent>, @Inject(MAT_DIALOG_DATA) public questions: Array<QuestionQuery>) {
    this.questions = questions;
  }

  ngOnInit() {

  }
}

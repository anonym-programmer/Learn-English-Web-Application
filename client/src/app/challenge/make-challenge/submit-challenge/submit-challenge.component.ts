import {Component, Inject, OnInit} from '@angular/core';
import {QuestionQuery} from "../../shared/question-query.model";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {SubmitChallengeDto} from "../../shared/submit-challenge-dto.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ChallengeService} from "../../shared/challenge.service";
import {SharedService} from "../../../shared/shared.service";

@Component({
  selector: 'app-submit-challenge',
  templateUrl: './submit-challenge.component.html',
  styleUrls: ['./submit-challenge.component.css']
})
export class SubmitChallengeComponent implements OnInit {

  dto = new SubmitChallengeDto();
  submitChallengeForm: FormGroup;

  options: string[] = ['a', 'b', 'c', 'd'];

  answer1: string;
  answer2: string;
  answer3: string;
  answer4: string;
  answer5: string;

  constructor(public dialogRef: MatDialogRef<SubmitChallengeComponent>,
              @Inject(MAT_DIALOG_DATA) public questions: Array<QuestionQuery>,
              private service: ChallengeService, private sharedService: SharedService) {
  }

  ngOnInit() {
    this.submitChallengeForm = new FormGroup({
      'answer1': new FormControl(this.answer1, [Validators.required]),
      'answer2': new FormControl(this.answer2, [Validators.required]),
      'answer3': new FormControl(this.answer3, [Validators.required]),
      'answer4': new FormControl(this.answer4, [Validators.required]),
      'answer5': new FormControl(this.answer5, [Validators.required]),
    });

    this.dto.defenderUsername = this.questions[5];
  }

  onSubmit(submitChallengeForm: FormGroup) {
    this.dto.questionsIds = [];
    this.dto.answers = [];

    for (let i=0; i<5; i++) {
      this.dto.questionsIds[i] = +submitChallengeForm.controls[`answer${i+1}`].value.split(":", 1);
      this.dto.answers[i] = submitChallengeForm.controls[`answer${i+1}`].value.split(":", 2)[1];
    }

    this.service.submit(this.dto).subscribe(
      () => {
        this.close();
        this.sharedService.showSuccessToastr('Successfully challenged person');
      }, () => {
        this.sharedService.showFailureToastr('Something went wrong');
      }
    );
  }

  close() {
    this.dialogRef.close();
  }
}

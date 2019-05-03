import {Component, Inject, OnInit} from '@angular/core';
import {QuestionQuery} from '../../shared/question-query.model';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {SubmitChallengeDto} from '../../shared/submit-challenge-dto.model';
import {FormControl, FormGroup} from '@angular/forms';
import {ChallengeService} from '../../shared/challenge.service';
import {SharedService} from '../../../shared/shared.service';
import {Constants} from '../../../shared/constants';

@Component({
  selector: 'app-submit-challenge',
  templateUrl: './submit-challenge.component.html',
  styleUrls: ['./submit-challenge.component.css']
})
export class SubmitChallengeComponent implements OnInit {

  dto = new SubmitChallengeDto();
  submitChallengeForm: FormGroup;

  options: string[] = ['a', 'b', 'c', 'd'];

  constructor(public dialogRef: MatDialogRef<SubmitChallengeComponent>,
              @Inject(MAT_DIALOG_DATA) public questions: Array<QuestionQuery>,
              private challengeService: ChallengeService, private sharedService: SharedService) {
  }

  ngOnInit() {
    this.dto.answers = [];
    this.submitChallengeForm = new FormGroup({
      'answer1': new FormControl(this.dto.answers[0]),
      'answer2': new FormControl(this.dto.answers[1]),
      'answer3': new FormControl(this.dto.answers[2]),
      'answer4': new FormControl(this.dto.answers[3]),
      'answer5': new FormControl(this.dto.answers[4]),
    });

    this.dto.defenderUsername = this.questions[5];
  }

  onSubmit(submitChallengeForm: FormGroup) {
    this.dto.questionsIds = [];

    for (let i = 0; i < 5; i++) {
      if (submitChallengeForm.controls[`answer${i + 1}`].value == null) {
        submitChallengeForm.controls[`answer${i + 1}`].setValue('*');
      }
      this.dto.questionsIds[i] = +submitChallengeForm.controls[`answer${i + 1}`].value.split(":", 1);
      this.dto.answers[i] = submitChallengeForm.controls[`answer${i + 1}`].value.split(":", 2)[1];
    }

    this.challengeService.submit(this.dto).subscribe(
      () => {
        this.sharedService.showSuccessToastr(Constants.CHALLENGED_PERSON);
        this.close();
        this.challengeService.notifyChallengeTypeChange();
      }, () => {
        this.sharedService.showFailureToastr(Constants.ANSWERS_EMPTY);
      }
    );
  }

  close() {
    this.dialogRef.close();
  }
}

import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {QuestionQuery} from '../../shared/question-query.model';
import {ChallengeService} from '../../shared/challenge.service';
import {SharedService} from '../../../shared/shared.service';
import {FormControl, FormGroup} from '@angular/forms';
import {SubmitPendingChallengeDto} from '../../shared/submit-pending-challenge-dto.model';
import {Constants} from '../../../shared/constants';

@Component({
  selector: 'app-submit-pending-challenge',
  templateUrl: './submit-pending-challenge.component.html',
  styleUrls: ['./submit-pending-challenge.component.css']
})
export class SubmitPendingChallengeComponent implements OnInit {

  dto = new SubmitPendingChallengeDto();
  submitPendingChallengeForm: FormGroup;

  options: string[] = ['a', 'b', 'c', 'd'];

  constructor(public dialogRef: MatDialogRef<SubmitPendingChallengeComponent>,
              @Inject(MAT_DIALOG_DATA) public questions: Array<QuestionQuery>,
              private challengeService: ChallengeService, private sharedService: SharedService) {
  }

  ngOnInit() {
    this.dto.answers = [];
    this.submitPendingChallengeForm = new FormGroup({
      'answer1': new FormControl(this.dto.answers[0]),
      'answer2': new FormControl(this.dto.answers[1]),
      'answer3': new FormControl(this.dto.answers[2]),
      'answer4': new FormControl(this.dto.answers[3]),
      'answer5': new FormControl(this.dto.answers[4]),
    });

    this.dto.challengeId = this.questions[5];
  }

  onSubmit(submitPendingChallengeForm: FormGroup) {
    this.dto.questionsIds = [];

    for (let i = 0; i < 5; i++) {
      if (submitPendingChallengeForm.controls[`answer${i + 1}`].value == null) {
        submitPendingChallengeForm.controls[`answer${i + 1}`].setValue('*');
      }
      this.dto.questionsIds[i] = +submitPendingChallengeForm.controls[`answer${i + 1}`].value.split(":", 1);
      this.dto.answers[i] = submitPendingChallengeForm.controls[`answer${i + 1}`].value.split(":", 2)[1];
    }

    this.challengeService.submitPendingChallenge(this.dto).subscribe(
      () => {
        this.close();
        this.sharedService.showSuccessToastr(Constants.COMPLETED_CHALLENGE);
      }, () => {
        this.sharedService.showFailureToastr(Constants.ANSWERS_EMPTY);
      }
    );
  }

  close() {
    this.dialogRef.close();
  }
}

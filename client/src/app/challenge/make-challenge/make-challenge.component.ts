import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material';
import {MakeChallengeDto} from '../shared/make-challenge-dto.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ChallengeService} from '../shared/challenge.service';
import {SharedService} from '../../shared/shared.service';
import {Constants} from '../../shared/constants';
import {SubmitChallengeComponent} from './submit-challenge/submit-challenge.component';

@Component({
  selector: 'app-make-challenge',
  templateUrl: './make-challenge.component.html',
  styleUrls: ['./make-challenge.component.css']
})
export class MakeChallengeComponent implements OnInit {

  dtoError = new MakeChallengeDto();
  makeChallengeForm: FormGroup;

  constructor(public dialogRef: MatDialogRef<MakeChallengeComponent>,
              @Inject(MAT_DIALOG_DATA) public dto: MakeChallengeDto,
              public dialog: MatDialog, private challengeService: ChallengeService,
              private sharedService: SharedService) {
  }

  ngOnInit() {
    this.makeChallengeForm = new FormGroup({
      'defenderUsername': new FormControl('', [Validators.required])
    });
  }

  getRandomRival() {
    this.challengeService.getRandomRival().subscribe(
      res => {
        this.makeChallengeForm.setValue({defenderUsername: res['defenderUsername']});
      }, () => {
        this.sharedService.showFailureToastr(Constants.SOMETHING_WRONG);
      }
    )
  }

  onSubmit(makeChallengeForm: FormGroup) {
    this.challengeService.make(makeChallengeForm.value).subscribe(
      () => {
        this.challengeService.getRandomQuestions().subscribe(res => {
          this.onNoClick();
          this.dialog.open(SubmitChallengeComponent, {
            width: '40%',
            height: '85%',
            data: res.concat(this.makeChallengeForm.controls['defenderUsername'].value)
          });
        });
      },
      error => {
        this.sharedService.showFailureToastr(Constants.INVALID_FIELD);
        this.dtoError = error.error;

        if (this.dtoError.defenderUsername != null) {
          this.makeChallengeForm.controls['defenderUsername'].reset();
        }
      }
    );
  }

  onNoClick() {
    this.dialogRef.close();
  }
}

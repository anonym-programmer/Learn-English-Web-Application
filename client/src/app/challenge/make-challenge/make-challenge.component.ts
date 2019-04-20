import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material";
import {MakeChallengeDto} from "../shared/make-challenge-dto.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ChallengeService} from "../shared/challenge.service";
import {SharedService} from "../../shared/shared.service";
import {Constants} from "../../shared/constants";
import {SubmitChallengeComponent} from "./submit-challenge/submit-challenge.component";

@Component({
  selector: 'app-make-challenge',
  templateUrl: './make-challenge.component.html',
  styleUrls: ['./make-challenge.component.css']
})
export class MakeChallengeComponent implements OnInit {

  dto = new MakeChallengeDto();
  dtoError = new MakeChallengeDto();
  makeChallengeForm: FormGroup;

  constructor(public dialogRef: MatDialogRef<MakeChallengeComponent>, @Inject(MAT_DIALOG_DATA) public dto: MakeChallengeDto,
              public dialog: MatDialog, private service: ChallengeService, private sharedService: SharedService) {
  }

  ngOnInit() {
    this.makeChallengeForm = new FormGroup({
      'defenderUsername': new FormControl(this.dto.defenderUsername, [Validators.required])
    });
  }

  get defenderUsername() {
    return this.makeChallengeForm.get('defenderUsername');
  }

  onSubmit(makeChallengeForm: FormGroup) {
    this.service.make(makeChallengeForm.value).subscribe(
      (res) => {
        this.onNoClick();
        this.dialog.open(SubmitChallengeComponent, {
          width: '50%',
          height: '80%',
          data: res
        })
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

  onNoClick(): void {
    this.dialogRef.close();
  }
}

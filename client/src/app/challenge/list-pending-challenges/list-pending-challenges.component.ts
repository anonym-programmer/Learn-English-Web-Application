import {Component, OnInit} from '@angular/core';
import {ChallengeService} from '../shared/challenge.service';
import {PendingChallengeQuery} from '../shared/pending-challenge-query.model';
import {SharedService} from '../../shared/shared.service';
import {Constants} from '../../shared/constants';
import {MatDialog} from '@angular/material';
import {SubmitPendingChallengeComponent} from './submit-pending-challenge/submit-pending-challenge.component';
import {ShowRivalProfileComponent} from "../show-rival-profile/show-rival-profile.component";

@Component({
  selector: 'app-list-pending-challenges',
  templateUrl: './list-pending-challenges.component.html',
  styleUrls: ['./list-pending-challenges.component.css']
})
export class ListPendingChallengesComponent implements OnInit {

  pendingChallenges = new Array<PendingChallengeQuery>();

  constructor(private challengeService: ChallengeService, private sharedService: SharedService,
              public dialog: MatDialog) {
  }

  ngOnInit() {
    this.getPendingChallenges();

    this.challengeService.challengeTypeChange_Observable.subscribe(() => {
      this.getPendingChallenges();
    });
  }

  getPendingChallenges() {
    this.challengeService.getPendingChallenges().subscribe(data => {
      this.pendingChallenges = data;
    });
  }

  accept(challengeId: string) {
    this.challengeService.getPendingChallengeById(challengeId).subscribe(data => {
      this.dialog.open(SubmitPendingChallengeComponent, {
        width: '40%',
        height: '85%',
        data: data.concat(challengeId),
        disableClose: true
      });
    });
  }

  decline(challengeId: string) {
    this.challengeService.declinePendingChallenge(challengeId).subscribe(() => {
      this.sharedService.showInfoToastr(Constants.DECLINED_CHALLENGE);
      this.getPendingChallenges();
    }, () => {
      this.sharedService.showFailureToastr(Constants.SOMETHING_WRONG);
    });
  }

  showRivalProfile(username: string) {
    this.challengeService.getRivalProfile(username).subscribe(data => {
      this.dialog.open(ShowRivalProfileComponent, {
        width: '20%',
        height: '38%',
        data: data
      });
    });
  }
}

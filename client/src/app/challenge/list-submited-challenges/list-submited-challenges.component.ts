import {Component, OnInit} from '@angular/core';
import {SubmitedChallengeQuery} from '../shared/submited-challenge-query.model';
import {ChallengeService} from '../shared/challenge.service';
import {ShowRivalProfileComponent} from "../show-rival-profile/show-rival-profile.component";
import {MatDialog} from "@angular/material";

@Component({
  selector: 'app-list-submited-challenges',
  templateUrl: './list-submited-challenges.component.html',
  styleUrls: ['./list-submited-challenges.component.css']
})
export class ListSubmitedChallengesComponent implements OnInit {

  submitedChallenges = new Array<SubmitedChallengeQuery>();

  constructor(private challengeService: ChallengeService, public dialog: MatDialog) {
  }

  ngOnInit() {
    this.getSubmitedChallenges();

    this.challengeService.challengeTypeChange_Observable.subscribe(() => {
      this.getSubmitedChallenges();
    });
  }

  getSubmitedChallenges() {
    this.challengeService.getSubmitedChallenges().subscribe(data => {
      this.submitedChallenges = data;
    });
  }

  showRivalProfile(username: string) {
    this.challengeService.getRivalProfile(username).subscribe(data => {
      this.dialog.open(ShowRivalProfileComponent, {
        width: '20%',
        height: '35%',
        data: data
      });
    });
  }
}

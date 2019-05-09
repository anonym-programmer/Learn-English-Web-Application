import {Component, OnInit} from '@angular/core';
import {CompletedChallengeQuery} from '../shared/completed-challenge-query.model';
import {ChallengeService} from '../shared/challenge.service';
import {MatDialog} from '@angular/material';
import {ShowCompletedChallengeDetailsComponent} from './show-completed-challenge-details/show-completed-challenge-details.component';
import {ShowRivalProfileComponent} from "../show-rival-profile/show-rival-profile.component";

@Component({
  selector: 'app-list-completed-challenges',
  templateUrl: './list-completed-challenges.component.html',
  styleUrls: ['./list-completed-challenges.component.css']
})
export class ListCompletedChallengesComponent implements OnInit {

  completedChallenges = new Array<CompletedChallengeQuery>();

  constructor(private challengeService: ChallengeService, private dialog: MatDialog) {
  }

  ngOnInit() {
    this.getCompletedChallenges();

    this.challengeService.challengeTypeChange_Observable.subscribe(() => {
      this.getCompletedChallenges();
    });
  }

  getCompletedChallenges() {
    this.challengeService.getCompletedChallenges().subscribe(data => {
      this.completedChallenges = data;
    });
  }

  viewDetails(id: string) {
    this.challengeService.getCompletedChallengeDetailsById(id).subscribe(data => {
      this.dialog.open(ShowCompletedChallengeDetailsComponent, {
        width: '35%',
        height: '60%',
        data: data
      });
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

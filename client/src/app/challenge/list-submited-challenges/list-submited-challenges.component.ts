import {Component, OnInit} from '@angular/core';
import {SubmitedChallengeQuery} from '../shared/submited-challenge-query.model';
import {ChallengeService} from '../shared/challenge.service';

@Component({
  selector: 'app-list-submited-challenges',
  templateUrl: './list-submited-challenges.component.html',
  styleUrls: ['./list-submited-challenges.component.css']
})
export class ListSubmitedChallengesComponent implements OnInit {

  submitedChallenges = new Array<SubmitedChallengeQuery>();

  constructor(private challengeService: ChallengeService) {
  }

  ngOnInit() {
    this.getSubmitedChallenges();
  }

  getSubmitedChallenges() {
    this.challengeService.getSubmitedChallenges().subscribe(data => {
      this.submitedChallenges = data;
    });
  }
}

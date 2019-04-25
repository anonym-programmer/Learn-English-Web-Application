import {Component, OnInit} from '@angular/core';
import {CompletedChallengeQuery} from "../shared/completed-challenge-query.model";
import {ChallengeService} from "../shared/challenge.service";

@Component({
  selector: 'app-list-completed-challenges',
  templateUrl: './list-completed-challenges.component.html',
  styleUrls: ['./list-completed-challenges.component.css']
})
export class ListCompletedChallengesComponent implements OnInit {

  completedChallenges = new Array<CompletedChallengeQuery>();

  constructor(private challengeService: ChallengeService) {
  }

  ngOnInit() {
    this.getCompletedChallenges();
  }

  getCompletedChallenges() {
    this.challengeService.getCompletedChallenges().subscribe(data => {
      this.completedChallenges = data;
    });
  }
}

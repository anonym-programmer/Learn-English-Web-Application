import {Component, OnInit} from '@angular/core';
import {ChallengeService} from "../shared/challenge.service";
import {PendingChallengeQuery} from "../shared/pending-challenge-query.model";
import {SharedService} from "../../shared/shared.service";
import {Constants} from "../../shared/constants";

@Component({
  selector: 'app-list-pending-challenges',
  templateUrl: './list-pending-challenges.component.html',
  styleUrls: ['./list-pending-challenges.component.css']
})
export class ListPendingChallengesComponent implements OnInit {

  pendingChallenges = new Array<PendingChallengeQuery>();

  constructor(private challengeService: ChallengeService, private sharedService: SharedService) {
  }

  ngOnInit() {
    this.getPendingChallenges();
  }

  getPendingChallenges() {
    this.challengeService.getPendingChallenges().subscribe(data => {
      this.pendingChallenges = data;
    })
  }

  decline(id: string) {
    this.challengeService.declinePendingChallenge(id).subscribe(() => {
      this.sharedService.showSuccessToastr(Constants.DECLINED_CHALLENGE);
      this.getPendingChallenges();
    }, () => {
      this.sharedService.showFailureToastr(Constants.SOMETHING_WRONG);
    })
  }
}

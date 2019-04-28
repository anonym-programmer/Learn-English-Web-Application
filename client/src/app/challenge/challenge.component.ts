import {Component} from '@angular/core';
import {MatDialog} from '@angular/material';
import {MakeChallengeComponent} from './make-challenge/make-challenge.component';

@Component({
  selector: 'app-challenge',
  templateUrl: './challenge.component.html',
  styleUrls: ['./challenge.component.css',
    '../forum/forum.component.css']
})
export class ChallengeComponent {

  constructor(public dialog: MatDialog) {
  }

  makeChallenge() {
    this.dialog.open(MakeChallengeComponent, {
      width: '250px',
      height: '300px'
    });
  }
}

import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {CreateCommentDto} from '../shared/create-comment-dto.model';
import {ActivatedRoute} from '@angular/router';
import {ForumService} from '../shared/forum.service';
import {ShowPostComponent} from '../show-post/show-post.component';
import {SharedService} from '../../shared/shared.service';
import {Constants} from '../../shared/constants';

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css',
              '../add-post/add-post.component.css']
})
export class AddCommentComponent implements OnInit {

  dto = new CreateCommentDto();
  dtoError = new CreateCommentDto();
  addCommentForm: FormGroup;
  id: string;

  constructor(private activatedRoute: ActivatedRoute, private forumService: ForumService,
              private sharedService: SharedService, private showPostComponent: ShowPostComponent) {
  }

  ngOnInit() {
    this.addCommentForm = new FormGroup({
      'text': new FormControl(this.dto.text, [Validators.required]),
    });
  }

  get text() {
    return this.addCommentForm.get('text');
  }

  addComment(addCommentForm: FormGroup) {
    this.activatedRoute.params.subscribe(param => {
      this.id = param['id'];
    });
    this.forumService.addComment(addCommentForm.controls['text'].value, this.id).subscribe(
      () => {
        this.sharedService.showSuccessToastr(Constants.ADDED_COMMENT);
        this.sharedService.showInfoToastr(Constants.ADDED_COMMENT_EXPERIENCE);
        let control: AbstractControl = null;
        addCommentForm.reset();
        addCommentForm.markAsUntouched();
        Object.keys(this.addCommentForm.controls).forEach(name => {
          control = this.addCommentForm.controls[name];
          control.setErrors(null);
        });
        this.showPostComponent.getPost();
      },
        error => {
          this.sharedService.showFailureToastr(Constants.INVALID_FIELDS);
          this.dtoError = error.error;

          if (this.dtoError.text != null) {
            this.addCommentForm.controls['text'].reset();
          }
        }
    );
  }
}

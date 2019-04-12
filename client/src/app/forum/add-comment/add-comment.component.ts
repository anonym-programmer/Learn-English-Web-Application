import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {CreateCommentDto} from '../shared/create-comment-dto.model';
import {ActivatedRoute} from '@angular/router';
import {ForumService} from '../shared/forum.service';
import {ToastrService} from 'ngx-toastr';
import {ShowPostComponent} from '../show-post/show-post.component';

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

  constructor(private activatedRoute: ActivatedRoute, private forumService: ForumService, private toastr: ToastrService,
              private showPostComponent: ShowPostComponent) {
  }

  ngOnInit() {
    this.addCommentForm = new FormGroup({
      'text': new FormControl(this.dto.text, [Validators.required]),
    });
  }

  get text() {
    return this.addCommentForm.get('text');
  }

  addComment() {
    this.activatedRoute.params.subscribe(param => {
      this.id = param['id'];
    });
    this.forumService.addComment(this.addCommentForm.controls['text'].value, this.id).subscribe(
      () => {
        this.showSuccess();
        let control: AbstractControl = null;
        this.addCommentForm.reset();
        this.addCommentForm.markAsUntouched();
        Object.keys(this.addCommentForm.controls).forEach((name) => {
          control = this.addCommentForm.controls[name];
          control.setErrors(null);
        });
        this.showPostComponent.getPost();
      },
        error => {
          this.showFailure();
          this.dtoError = error.error;

          if (this.dtoError.text != null) {
            this.addCommentForm.controls['text'].reset();
          }
      }
    )
  }

  private showSuccess() {
    this.toastr.success('Successfully added new post!', 'Success');
  }

  private showFailure() {
    this.toastr.error('Correct invalid fields.', 'Failure');
  }
}

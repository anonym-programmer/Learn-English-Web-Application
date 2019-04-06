import {Component, OnInit} from '@angular/core';
import {ForumService} from '../shared/forum.service';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';
import {CreatePostDto} from '../shared/create-post-dto.model';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  dto = new CreatePostDto();
  dtoError = new CreatePostDto();
  addPostForm: FormGroup;

  constructor(private forumService: ForumService, private toastr: ToastrService, private router: Router) {
  }

  ngOnInit() {
    this.addPostForm = new FormGroup({
      'title': new FormControl(this.dto.title, [Validators.required]),
      'description': new FormControl(this.dto.description, [Validators.required])
    });
  }

  get title() {
    return this.addPostForm.get('title');
  }

  get description() {
    return this.addPostForm.get('description');
  }

  addPost() {
    this.forumService.addPost(this.addPostForm.value).subscribe(
      () => {
        this.showSuccess();
        let control: AbstractControl = null;
        this.addPostForm.reset();
        this.addPostForm.markAsUntouched();
        Object.keys(this.addPostForm.controls).forEach((name) => {
          control = this.addPostForm.controls[name];
          control.setErrors(null);
        });
      },
      error => {
        this.showFailure();
        this.dtoError = error.error;

        if (this.dtoError.title != null) {
          this.addPostForm.controls['title'].reset();
        }

        if (this.dtoError.description != null) {
          this.addPostForm.controls['description'].reset();
        }
      }
    );
  }

  private showSuccess() {
    this.toastr.success('Successfully added new post!', 'Success');
  }

  private showFailure() {
    this.toastr.error('Correct invalid fields.', 'Failure');
  }
}

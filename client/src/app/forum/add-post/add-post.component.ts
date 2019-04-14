import {Component, OnInit} from '@angular/core';
import {ForumService} from '../shared/forum.service';
import {CreatePostDto} from '../shared/create-post-dto.model';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {SharedService} from "../../shared/shared.service";
import {Constants} from "../../shared/constants";

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  dto = new CreatePostDto();
  dtoError = new CreatePostDto();
  addPostForm: FormGroup;

  constructor(private forumService: ForumService, private sharedService: SharedService) {
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

  addPost(addPostForm: FormGroup) {
    this.forumService.addPost(addPostForm.value).subscribe(
      () => {
        this.sharedService.showSuccessToastr(Constants.ADDED_POST);
        let control: AbstractControl = null;
        this.addPostForm.reset();
        this.addPostForm.markAsUntouched();
        Object.keys(this.addPostForm.controls).forEach((name) => {
          control = this.addPostForm.controls[name];
          control.setErrors(null);
        });
      },
      error => {
        this.sharedService.showFailureToastr(Constants.INVALID_FIELDS);
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
}

import {Component, Inject, OnInit} from '@angular/core';
import {ForumService} from '../shared/forum.service';
import {CreatePostDto} from '../shared/create-post-dto.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {SharedService} from "../../shared/shared.service";
import {Constants} from "../../shared/constants";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  dtoError = new CreatePostDto();
  addPostForm: FormGroup;

  constructor(public dialogRef: MatDialogRef<AddPostComponent>, @Inject(MAT_DIALOG_DATA) public dto: CreatePostDto,
              private forumService: ForumService, private sharedService: SharedService) {
  }

  ngOnInit() {
    this.addPostForm = new FormGroup({
      'title': new FormControl('', [Validators.required]),
      'description': new FormControl('', [Validators.required])
    });
  }

  addPost(addPostForm: FormGroup) {
    this.forumService.addPost(addPostForm.value).subscribe(
      () => {
        this.sharedService.showSuccessToastr(Constants.ADDED_POST);
        this.onNoClick();
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

  onNoClick() {
    this.dialogRef.close();
  }
}

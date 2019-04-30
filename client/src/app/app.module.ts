import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './app-routing.module';
import {MaterialModule} from './material.module';
import {ToastrModule} from 'ngx-toastr';
import {SweetAlert2Module} from '@toverux/ngx-sweetalert2';

import {AppComponent} from './app.component';

import {HomeComponent} from './home/home.component';

import {ForumComponent} from './forum/forum.component';
import {RegisterComponent} from './base/register/register.component';
import {CredentialsComponent} from './base/credentials/credentials.component';
import {ConfirmComponent} from './base/token/account/confirm.component';
import {ResetComponent} from './base/token/password/reset.component';
import {LoginComponent} from './base/login/login.component';

import {ProfileComponent} from './user/profile/own/profile.component';
import {SomeoneProfileComponent} from './user/profile/someone/someone-profile.component';
import {ChangePasswordComponent} from './user/profile/own/settings/change-password/change-password.component';
import {ChangeEmailComponent} from './user/profile/own/settings/change-email/change-email.component';
import {AddPostComponent} from './forum/add-post/add-post.component';
import {AddCommentComponent} from './forum/add-comment/add-comment.component';
import {ListPostComponent} from './forum/list-post/list-post.component';
import {ShowPostComponent} from './forum/show-post/show-post.component';

import {AdminComponent} from './admin/admin.component';

import {ChallengeComponent} from './challenge/challenge.component';
import {MakeChallengeComponent} from './challenge/make-challenge/make-challenge.component';
import {SubmitChallengeComponent} from './challenge/make-challenge/submit-challenge/submit-challenge.component';
import {SubmitPendingChallengeComponent} from './challenge/list-pending-challenges/submit-pending-challenge/submit-pending-challenge.component';
import {ListPendingChallengesComponent} from './challenge/list-pending-challenges/list-pending-challenges.component';
import {ListSubmitedChallengesComponent} from './challenge/list-submited-challenges/list-submited-challenges.component';
import {ListCompletedChallengesComponent} from './challenge/list-completed-challenges/list-completed-challenges.component';
import {ShowCompletedChallengeDetailsComponent} from './challenge/list-completed-challenges/show-completed-challenge-details/show-completed-challenge-details.component';
import {ShowCorrectAnswersChallengeDetailsComponent} from './challenge/list-completed-challenges/show-completed-challenge-details/show-correct-answers-challenge/show-correct-answers-challenge-details.component';
import {ShowRivalProfileComponent} from './challenge/show-rival-profile/show-rival-profile.component';

import {NotAuthGuard} from './auth/not-auth.guard';
import {AuthGuard} from './auth/auth.guard';
import {RoleGuard} from './auth/role.guard';

import {BaseService} from './base/shared/base.service';
import {AuthService} from './auth/auth.service';
import {CookieService} from 'ngx-cookie-service';
import {UserService} from './user/shared/user.service';
import {ForumService} from './forum/shared/forum.service';
import {AdminService} from './admin/shared/admin.service';
import {SharedService} from './shared/shared.service';
import {CustomInterceptor} from './auth/http-interceptor';
import {ChallengeService} from './challenge/shared/challenge.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ForumComponent,
    RegisterComponent,
    CredentialsComponent,
    ConfirmComponent,
    ResetComponent,
    LoginComponent,
    ProfileComponent,
    SomeoneProfileComponent,
    ChangePasswordComponent,
    ChangeEmailComponent,
    AddPostComponent,
    AddCommentComponent,
    ListPostComponent,
    ShowPostComponent,
    AdminComponent,
    ChallengeComponent,
    MakeChallengeComponent,
    SubmitChallengeComponent,
    SubmitPendingChallengeComponent,
    ListPendingChallengesComponent,
    ListSubmitedChallengesComponent,
    ListCompletedChallengesComponent,
    ShowCompletedChallengeDetailsComponent,
    ShowCorrectAnswersChallengeDetailsComponent,
    ShowRivalProfileComponent,
  ],
  entryComponents: [
    AddPostComponent,
    MakeChallengeComponent,
    SubmitChallengeComponent,
    SubmitPendingChallengeComponent,
    ShowCompletedChallengeDetailsComponent,
    ShowCorrectAnswersChallengeDetailsComponent,
    ShowRivalProfileComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    MaterialModule,
    ToastrModule.forRoot(),
    SweetAlert2Module.forRoot()
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: CustomInterceptor,
      multi: true
    },
    NotAuthGuard,
    AuthGuard,
    RoleGuard,
    BaseService,
    AuthService,
    CookieService,
    UserService,
    ForumService,
    AdminService,
    SharedService,
    ChallengeService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

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

import {DashboardComponent} from './dashboard/dashboard.component';
import {ForumComponent} from './forum/forum.component';
import {RegisterComponent} from './base/register/register.component';
import {CredentialsComponent} from './base/credentials/credentials.component';
import {ConfirmComponent} from './base/token/account/confirm.component';
import {ResetComponent} from './base/token/password/reset.component';
import {LoginComponent} from './base/login/login.component';

import {ProfileComponent} from './user/profile/own/profile.component';
import {SomeoneProfileComponent} from './user/profile/someone/someone-profile.component';
import {SettingsComponent} from './user/profile/own/settings/settings.component';
import {ChangePasswordComponent} from './user/profile/own/settings/change-password/change-password.component';
import {ChangeEmailComponent} from './user/profile/own/settings/change-email/change-email.component';
import {AddPostComponent} from './forum/add-post/add-post.component';
import {AddCommentComponent} from './forum/add-comment/add-comment.component';
import {ListPostComponent} from './forum/list-post/list-post.component';
import {ShowPostComponent} from './forum/show-post/show-post.component';

import {AdminComponent} from './admin/admin.component';

import {AuthGuard} from './auth/auth.guard';
import {RoleGuard} from "./auth/role.guard";

import {BaseService} from './base/shared/base.service';
import {AuthService} from './auth/auth.service';
import {CookieService} from 'ngx-cookie-service';
import {UserService} from './user/shared/user.service';
import {ForumService} from './forum/shared/forum.service';
import {AdminService} from "./admin/shared/admin.service";
import {SharedService} from "./shared/shared.service";
import {CustomInterceptor} from "./auth/http-interceptor";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    ForumComponent,
    RegisterComponent,
    CredentialsComponent,
    ConfirmComponent,
    ResetComponent,
    LoginComponent,
    ProfileComponent,
    SomeoneProfileComponent,
    SettingsComponent,
    ChangePasswordComponent,
    ChangeEmailComponent,
    AddPostComponent,
    AddCommentComponent,
    ListPostComponent,
    ShowPostComponent,
    AdminComponent,
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
      useClass: CustomInterceptor ,
      multi: true
    },
    AuthGuard,
    RoleGuard,
    BaseService,
    AuthService,
    CookieService,
    UserService,
    ForumService,
    AdminService,
    SharedService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

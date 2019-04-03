import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
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

import {ProfileComponent} from './user/profile/profile.component';
import {ChangePasswordComponent} from './user/profile/change-password/change-password.component';
import {ChangeEmailComponent} from './user/profile/change-email/change-email.component';

import {AuthGuard} from './auth/auth.guard';

import {BaseService} from './base/shared/base.service';
import {AuthService} from './auth/auth.service';
import {CookieService} from 'ngx-cookie-service';
import {UserService} from './user/shared/user.service';


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
    ChangePasswordComponent,
    ChangeEmailComponent
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
    AuthGuard,
    BaseService,
    AuthService,
    CookieService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

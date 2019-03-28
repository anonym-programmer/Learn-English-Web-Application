import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './app-routing.module';
import {MaterialModule} from './material.module';
import {ToastrModule} from 'ngx-toastr';
import {SweetAlert2Module} from '@toverux/ngx-sweetalert2';

import {AppComponent} from './app.component';
import {RegisterComponent} from './base/register/register.component';

import {UserService} from './base/shared/user.service';
import {ConfirmComponent} from './base/token/account/confirm.component';
import {LoginComponent} from './base/login/login.component';
import {ResetComponent} from './base/token/password/reset.component';
import {CredentialsComponent} from './base/credentials/credentials.component';
import {AuthGuard} from './auth/auth.guard';
import {AuthService} from './auth/auth.service';
import {DashboardComponent} from './user/dashboard/dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    CredentialsComponent,
    ConfirmComponent,
    ResetComponent,
    LoginComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    MaterialModule,
    ToastrModule.forRoot(),
    SweetAlert2Module.forRoot()
  ],
  providers: [
    UserService,
    AuthGuard,
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

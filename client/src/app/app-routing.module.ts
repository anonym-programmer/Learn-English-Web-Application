import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from './base/register/register.component';
import {ConfirmComponent} from './base/token/account/confirm.component';
import {LoginComponent} from './base/login/login.component';
import {ResetComponent} from './base/token/password/reset.component';
import {CredentialsComponent} from './base/credentials/credentials.component';
import {DashboardComponent} from './user/dashboard/dashboard.component';
import {AuthGuard} from './auth/auth.guard';
import {ProfileComponent} from './user/profile/profile.component';
import {ChangePasswordComponent} from './user/profile/change-password/change-password.component';
import {ChangeEmailComponent} from './user/profile/change-email/change-email.component';

const routes: Routes = [
  {path: '*', redirectTo: '/'},
  {path: 'register', component: RegisterComponent},
  {path: 'confirm-account', component: ConfirmComponent},
  {path: 'forgot-credentials', component: CredentialsComponent},
  {path: 'reset-password', component: ResetComponent},
  {path: 'login', component: LoginComponent},

  {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
  {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
  {path: 'profile/change-password', component: ChangePasswordComponent, canActivate: [AuthGuard]},
  {path: 'profile/change-email', component: ChangeEmailComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }

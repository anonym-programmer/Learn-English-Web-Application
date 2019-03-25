import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from './user/register/register.component';
import {ConfirmComponent} from './user/token/account/confirm.component';
import {LoginComponent} from './user/login/login.component';
import {ResetComponent} from './user/token/password/reset.component';
import {CredentialsComponent} from './user/credentials/credentials.component';

const routes: Routes = [
  {path: '*', redirectTo: '/'},
  {path: 'register', component: RegisterComponent},
  {path: 'confirm-account', component: ConfirmComponent},
  {path: 'forgot-credentials', component: CredentialsComponent},
  {path: 'reset-password', component: ResetComponent},
  {path: 'login', component: LoginComponent}
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

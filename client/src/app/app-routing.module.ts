import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from './user/register/register.component';
import {ConfirmComponent} from './user/confirm/confirm.component';
import {LoginComponent} from './user/login/login.component';

const routes: Routes = [
  {path: '*', redirectTo: '/'},
  {path: 'register', component: RegisterComponent},
  {path: 'confirm-account', component: ConfirmComponent},
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
export class AppRoutingModule {
}

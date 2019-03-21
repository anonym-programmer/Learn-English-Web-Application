import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from './user/register/register.component';
import {ConfirmComponent} from './user/confirm/confirm.component';

const routes: Routes = [
  {path: '*', redirectTo: '/'},
  {path: 'register', component: RegisterComponent},
  {path: 'confirm-account', component: ConfirmComponent}
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

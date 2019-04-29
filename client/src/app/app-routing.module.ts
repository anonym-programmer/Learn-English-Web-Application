import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ForumComponent} from './forum/forum.component';
import {RegisterComponent} from './base/register/register.component';
import {ConfirmComponent} from './base/token/account/confirm.component';
import {LoginComponent} from './base/login/login.component';
import {ResetComponent} from './base/token/password/reset.component';
import {CredentialsComponent} from './base/credentials/credentials.component';
import {AuthGuard} from './auth/auth.guard';
import {ProfileComponent} from './user/profile/own/profile.component';
import {SomeoneProfileComponent} from './user/profile/someone/someone-profile.component';
import {ShowPostComponent} from './forum/show-post/show-post.component';
import {AdminComponent} from './admin/admin.component';
import {RoleGuard} from './auth/role.guard';
import {NotAuthGuard} from './auth/not-auth.guard';
import {ChallengeComponent} from './challenge/challenge.component';

const routes: Routes = [
  {path: '*', redirectTo: '/'},

  {path: 'forum', component: ForumComponent},

  {
    path: '',
    canActivate: [NotAuthGuard],
    children: [
      {path: 'register', component: RegisterComponent},
      {path: 'confirm-account', component: ConfirmComponent},
      {path: 'forgot-credentials', component: CredentialsComponent},
      {path: 'reset-password', component: ResetComponent},
      {path: 'login', component: LoginComponent},
    ]
  },

  {
    path: '',
    canActivate: [AuthGuard],
    children: [
      {path: 'profile/:username', component: SomeoneProfileComponent},
      {path: 'my-profile', component: ProfileComponent},
      {path: 'admin-panel', component: AdminComponent, canActivate: [RoleGuard]},
      {path: 'challenge', component: ChallengeComponent}
    ],
  },

  {path: 'forum/post/:id', component: ShowPostComponent},
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

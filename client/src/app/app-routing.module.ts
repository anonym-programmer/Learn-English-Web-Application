import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashboardComponent} from './dashboard/dashboard.component';
import {ForumComponent} from './forum/forum.component';
import {RegisterComponent} from './base/register/register.component';
import {ConfirmComponent} from './base/token/account/confirm.component';
import {LoginComponent} from './base/login/login.component';
import {ResetComponent} from './base/token/password/reset.component';
import {CredentialsComponent} from './base/credentials/credentials.component';
import {AuthGuard} from './auth/auth.guard';
import {ProfileComponent} from './user/profile/own/profile.component';
import {ChangePasswordComponent} from './user/profile/own/settings/change-password/change-password.component';
import {ChangeEmailComponent} from './user/profile/own/settings/change-email/change-email.component';
import {AddPostComponent} from './forum/add-post/add-post.component';
import {SettingsComponent} from './user/profile/own/settings/settings.component';
import {SomeoneProfileComponent} from './user/profile/someone/someone-profile.component';
import {ShowPostComponent} from './forum/show-post/show-post.component';
import {AdminComponent} from "./admin/admin.component";

const routes: Routes = [
  {path: '*', redirectTo: '/dashboard'},

  {path: 'dashboard', component: DashboardComponent},
  {path: 'forum', component: ForumComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'confirm-account', component: ConfirmComponent},
  {path: 'forgot-credentials', component: CredentialsComponent},
  {path: 'reset-password', component: ResetComponent},
  {path: 'login', component: LoginComponent},

  {path: 'profile/:username', component: SomeoneProfileComponent, canActivate: [AuthGuard]},
  {path: 'my-profile', component: ProfileComponent, canActivate: [AuthGuard]},
  {path: 'my-profile/settings', component: SettingsComponent, canActivate: [AuthGuard]},
  {path: 'my-profile/settings/change-password', component: ChangePasswordComponent, canActivate: [AuthGuard]},
  {path: 'my-profile/settings/change-email', component: ChangeEmailComponent, canActivate: [AuthGuard]},

  {path: 'forum/add-post', component: AddPostComponent, canActivate: [AuthGuard]},
  {path: 'forum/post/:id', component: ShowPostComponent},

  {path: 'admin-panel', component: AdminComponent}
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

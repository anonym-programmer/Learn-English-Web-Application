import {NgModule} from '@angular/core';

import {
  MatToolbarModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule,
  MatButtonModule,
  MatCheckboxModule
} from '@angular/material';

import {FormsModule} from '@angular/forms';

@NgModule({
  imports: [
    MatToolbarModule,
    MatCardModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    MatButtonModule,
    MatCheckboxModule
  ],
  exports: [
    MatToolbarModule,
    MatCardModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    MatButtonModule,
    MatCheckboxModule
  ]
})

export class MaterialModule {
}

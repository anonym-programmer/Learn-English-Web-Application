import { NgModule } from '@angular/core';

import {
    MatToolbarModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
} from '@angular/material';

import { FormsModule } from '@angular/forms';

@NgModule({
    imports: [
        MatToolbarModule,
        MatCardModule,
        MatFormFieldModule,
        FormsModule,
        MatInputModule,
        MatButtonModule
    ],
    exports: [
        MatToolbarModule,
        MatCardModule,
        MatFormFieldModule,
        FormsModule,
        MatInputModule,
        MatButtonModule
    ]
})

export class MaterialModule {
}

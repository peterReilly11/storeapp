import { BrowserModule } from '@angular/platform-browser';
import { NgModule, OnInit } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatGridListModule} from '@angular/material/grid-list';
import { CartComponent } from './cart/cart.component';
import { HttpClientModule } from '@angular/common/http';
import { CartItemComponent } from './cart-item/cart-item.component';
import { AuthRoutingModule } from './auth-routing.module';
import { ApodComponent } from './apod/apod.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatExpansionModule} from '@angular/material/expansion';
import { MyCartHistoryComponent } from './my-cart-history/my-cart-history.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { JournalComponent } from './journal/journal.component';
import {MatDialogModule} from '@angular/material/dialog';
import {JournalModal} from './journal/journal.component'
import { FormsModule } from '@angular/forms';
import {MatTabsModule} from '@angular/material/tabs';
import { JournalEntryComponent } from './journal-entry/journal-entry.component';


@NgModule({
  declarations: [
    AppComponent,
    CartComponent,
    CartItemComponent,
    ApodComponent,
    MyCartHistoryComponent,
    JournalComponent,
    JournalModal,
    JournalEntryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    MatIconModule,
    MatGridListModule,
    AuthRoutingModule,
    MatDatepickerModule,
    MatNativeDateModule,
    BrowserAnimationsModule,
    MatExpansionModule,
    MatSnackBarModule,
    MatDialogModule,
    FormsModule,
    MatTabsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents : [
    JournalModal
  ]
})
export class AppModule{ 
}

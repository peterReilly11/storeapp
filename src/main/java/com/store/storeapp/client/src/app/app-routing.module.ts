import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CartComponent } from './cart/cart.component';
import { MyCartHistoryComponent } from './my-cart-history/my-cart-history.component';
import { OktaAuthGuard } from '@okta/okta-angular';
import { ApodComponent } from './apod/apod.component';
import { JournalComponent } from './journal/journal.component';

const routes: Routes = [
  {
    path : 'cart',
    component : CartComponent,
    canActivate : [OktaAuthGuard]
  },
  {
    path : 'apod',
    component : ApodComponent
  },
  {
    path : 'my-cart-history',
    component : MyCartHistoryComponent,
    canActivate : [OktaAuthGuard]
  },
  {
    path : 'journal',
    component : JournalComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

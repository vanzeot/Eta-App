import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NotificationListComponent } from './components/notification-list/notification-list.component';
import { HttpClientModule } from '@angular/common/http';
import { NotificationService } from './services/notification.service';
import { Routes, RouterModule } from '@angular/router';
import { OrderListComponent } from './components/order-list/order-list.component';
import { NotificationDetailsComponent } from './components/notification-details/notification-details.component';
import { ConfirmationListComponent } from './components/confirmation-list/confirmation-list.component';
import { OrderDetailsComponent } from './components/order-details/order-details.component';

const routes: Routes = [
  { path: 'notifications/:number', component: NotificationDetailsComponent},
  { path: 'notifications', component: NotificationListComponent},
  { path: 'orders', component: OrderListComponent},
  { path: 'orders/:number', component: OrderDetailsComponent},
  
  // ...
  { path: '', redirectTo: '/notifications', pathMatch: 'full'},
  { path: '**', redirectTo: '/notifications', pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    NotificationListComponent,
    OrderListComponent,
    NotificationDetailsComponent,
    ConfirmationListComponent,
    OrderDetailsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [NotificationService],
  bootstrap: [AppComponent]
})
export class AppModule { }

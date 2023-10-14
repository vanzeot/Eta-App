import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NotificationListComponent } from './components/notification-list/notification-list.component';
import { HttpClientModule } from '@angular/common/http';
import { NotificationService } from './services/notification.service';
@NgModule({
  declarations: [
    AppComponent,
    NotificationListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [NotificationService],
  bootstrap: [AppComponent]
})
export class AppModule { }

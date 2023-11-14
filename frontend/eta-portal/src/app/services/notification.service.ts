import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Notification } from '../common/notification';
import { ExibitionNotification } from '../common/exibition-notification';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private repoUrl = 'http://localhost:8080/api/notifications';
  private controllerUrl = 'http://localhost:8080/api/notification';

  constructor(private httpClient: HttpClient){
  }

  getNotificationList(): Observable<Notification[]>{
    return this.httpClient.get<GetResponse>(this.repoUrl).pipe(
      map(response => response._embedded.notifications)
    );
  }


  getNotification(theNotificationNumber: string): Observable<ExibitionNotification> {

    const notificationUrl = `${this.controllerUrl}/${theNotificationNumber}`;

    return this.httpClient.get<ExibitionNotification>(notificationUrl);
  }

}

interface GetResponse {
  _embedded: {
    notifications: Notification[];
  }
}

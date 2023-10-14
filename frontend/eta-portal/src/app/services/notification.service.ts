import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Notification } from '../common/notification';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private baseUrl = 'http://localhost:8080/api/notifications';

  constructor(private httpClient: HttpClient){
  }

  getNotificationList(): Observable<Notification[]>{
    return this.httpClient.get<GetResponse>(this.baseUrl).pipe(
      map(response => response._embedded.notifications)
    );
  }
}

interface GetResponse {
  _embedded: {
    notifications: Notification[];
  }
}

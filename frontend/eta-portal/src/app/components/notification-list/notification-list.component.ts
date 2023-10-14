import { Component, OnInit } from '@angular/core';
import { Notification } from 'src/app/common/notification';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-notification-list',
  templateUrl: './notification-list.component.html',
  styleUrls: ['./notification-list.component.css']
})
export class NotificationListComponent implements OnInit {

  notifications: Notification[] = [];

  constructor(private notificationService: NotificationService) { }

  ngOnInit(): void {
    this.listNotifications();
  }

  listNotifications() {
    this.notificationService.getNotificationList().subscribe(
      data => {
        this.notifications = data;
      }
    )
  }

}

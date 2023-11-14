import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ExibitionNotification } from 'src/app/common/exibition-notification';
import { Notification } from 'src/app/common/notification';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-notification-details',
  templateUrl: './notification-details.component.html',
  styleUrls: ['./notification-details.component.css']
})
export class NotificationDetailsComponent implements OnInit {

  notification!: ExibitionNotification;
  // product: Product = new Product();

  constructor(private productService: NotificationService,
    private route: ActivatedRoute) { }


  ngOnInit(): void {
    this.route.paramMap.subscribe(()=> {
      this.handleNotificationDetails();
    })
  }

  handleNotificationDetails() {

    const theNotificationNumber: string = this.route.snapshot.paramMap.get('number')!;

    this.productService.getNotification(theNotificationNumber).subscribe(
      data => {
        this.notification = data;
      }
    )
  }

}

import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/common/order';
import { ExecutionService } from 'src/app/services/execution.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

  orders: Order[] = [];

  constructor(private executionService: ExecutionService) { }

  ngOnInit(): void {
    this.listOrders();
  }

  listOrders(){
    this.executionService.getOrderList().subscribe(
      data => {
        this.orders = data;
      }
    )
  }

}

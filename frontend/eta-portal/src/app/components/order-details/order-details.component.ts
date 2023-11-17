import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ExibitionOrder } from 'src/app/common/exibition/exibition-order';
import { ExecutionService } from 'src/app/services/execution.service';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  order!: ExibitionOrder;

  constructor(private executionService: ExecutionService,
    private route: ActivatedRoute
    ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=> {
      this.handleOrderDetails();
    })
  }

  handleOrderDetails() {

    const theOrderNumber: string = this.route.snapshot.paramMap.get('number')!;

    this.executionService.getOrder(theOrderNumber).subscribe(
      data => {
        this.order = data;
      }
    )
  }

}

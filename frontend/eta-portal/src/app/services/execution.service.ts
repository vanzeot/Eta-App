import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Order } from '../common/order';
import { Confirmation } from '../common/confirmation';
import { ExibitionOrder } from '../common/exibition/exibition-order';

@Injectable({
  providedIn: 'root'
})
export class ExecutionService {

  private baseUrl = 'http://localhost:8080/api/';
  private controllerUrl = 'http://localhost:8080/api/execution'

  constructor(private httpClient: HttpClient) {

  }

  getOrderList(): Observable<Order[]>{
    return this.httpClient.get<GetOrderResponse>(this.baseUrl+'orders').pipe(
      map(response => response._embedded.orders)
    );
  }

  getConfirmationList(): Observable<Confirmation[]>{
    return this.httpClient.get<GetConfirmationResponse>(this.baseUrl+'confirmations').pipe(
      map(response => response._embedded.orders)
    );
  }

  getOrder(theOrderNumber: string): Observable<ExibitionOrder> {

    const orderUrl = `${this.controllerUrl}/${theOrderNumber}`;

    return this.httpClient.get<ExibitionOrder>(orderUrl);
  }

}

interface GetOrderResponse {
  _embedded: {
    orders: Order[];
  }
}

interface GetConfirmationResponse {
  _embedded: {
    orders: Confirmation[];
  }
}
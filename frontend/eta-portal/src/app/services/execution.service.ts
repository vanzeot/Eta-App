import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Order } from '../common/order';
import { Confirmation } from '../common/confirmation';

@Injectable({
  providedIn: 'root'
})
export class ExecutionService {

  private baseUrl = 'http://localhost:8080/api/';

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
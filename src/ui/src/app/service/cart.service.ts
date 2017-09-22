import { Injectable } from '@angular/core';
import { Http, Response} from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { CartItem } from '../model/cart-item';
import { environment as env } from '../../environments/environment';

@Injectable()
export class CartService {

  constructor(private http: Http) { }

  public getCart(): Observable<CartResponse> {
    return this.http.get(`${env.appUrl}/cart`)
      .map(res => {
        console.log(res.json());
        const body: any = res.json();
        return { err: null, cartitems: body};
      })
      .catch(err => {
        console.log('Server error: ' + JSON.stringify(err, null, 2));
        return Observable.of({err: JSON.stringify(err, null, 2), cartitems: null});
      })
      ;
    }

public emptyCart() : Observable<CartValidateResponse>{
  return this.http.post(`${env.appUrl}/cart`,`requestid : "2"`)
    .map(res => {
      const body: any = res.json();
      return { err: null, state: body};
    })
    .catch(err => {
      console.log('Server error: ' + JSON.stringify(err, null, 2));
      return Observable.of({err: JSON.stringify(err, null, 2), state: null});
    })
    ;
}
public validateCart() : Observable<CartValidateResponse>{
  return this.http.post(`${env.appUrl}/cart`,`requestid : "3"`)
    .map(res => {
      const body: any = res.json();
      return { err: null, state: body};
    })
    .catch(err => {
      console.log('Server error: ' + JSON.stringify(err, null, 2));
      return Observable.of({err: JSON.stringify(err, null, 2), state: null});
    })
    ;
}
}

export interface CartResponse {
  err: any;
  cartitems: CartItem[];
}

export interface CartValidateResponse {
  err: any;
  state:number;
}

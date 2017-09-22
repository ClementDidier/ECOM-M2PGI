import { Injectable } from '@angular/core';
import { Http, Response} from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { CartItem } from '../model/cart-item';
import { environment as env } from '../../environments/environment';

@Injectable()
export class BookService {

  constructor(private http: Http) { }

  public pay(numCarte : number) : Observable<BookResponse>{
  return this.http.post(`${env.appUrl}/book`,`card : ${numCarte}`)
      .map(res => {
        const body: any = res.json();
        return { err: null, state: body.state, excart: body.excart,booknb : body.booknb};
      })
      .catch(err => {
        console.log('Server error: ' + JSON.stringify(err, null, 2));
        return Observable.of({err: JSON.stringify(err, null, 2), state:null, excart:null});
      })
      ;
  }
}
export interface BookResponse {
  err: any;
  state: number;
  excart : CartItem[];
  booknb : number;
}

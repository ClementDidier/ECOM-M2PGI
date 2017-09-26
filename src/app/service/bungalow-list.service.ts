import { Injectable } from '@angular/core';
import { Http, Response} from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { environment as env } from '../../environments/environment';

@Injectable()
export class BungalowListService {

  constructor(private http: Http) { }

  public putCart(requestid, bungalowid, startweek, endweek, durations) : Observable<validRes>{
    
	
	var parameter = JSON.stringify({requestid:1, bungalowid: bungalowid , startweek: startweek, endweek: endweek, durations: durations})
    return this.http.post(`${env.appUrl}/cart`, parameter).map(response => response.json());
	  
  }
}

export interface validRes {
  err: any;
  state: number;
}
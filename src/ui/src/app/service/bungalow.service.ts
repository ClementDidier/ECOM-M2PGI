import { Injectable } from '@angular/core';
import { Http, Response} from '@angular/http';
import { environment as env } from '../../environments/environment';
import { Observable } from 'rxjs/Rx';
import { Bungalow } from './../model/bungalow';
@Injectable()
export class BungalowService {

  constructor(private http: Http) { }

  public all(): Observable<BungalowsResponse> {
    return this.http.get(`${env.appUrl}/bungalows`)
      .map(res => {
        const body: any = res.json();
        return { err: null, bungalows: body._embedded.bungalows};
      })
      .catch(err => {
        console.log('Server error: ' + JSON.stringify(err, null, 2));
        return Observable.of({err: err, bungalows: null});
      })
      ;
    }

    public getBungalow(bungalowId: string): Observable<BungalowResponse> {
      return this.http.get(`${env.appUrl}/bungalows/${bungalowId}`)
        .map(res => {
          console.log(res.json());
          const body: any = res.json();
          console.log(JSON.stringify(body, null, 2));
          return { err: null, bungalow: body};
        })
        .catch(err => {
          console.log('Server error: ' + JSON.stringify(err, null, 2));
          return Observable.of({err: err, bungalow: null});
        })
        ;
    }
    public getBungalows(bungalow: Bungalow) : Observable<BungalowsResponse> {
      return this.http.get(`${env.appUrl}/bungalows?bedcount=${bungalow.bedcount}&islandid=${bungalow.islandid}&maxprice=${bungalow.maxprice}`)
        .map(res => {
          const body: any = res.json();
          return { err: null, bungalows: body._embedded.bungalows};
        })
        .catch(err => {
          console.log('Server error: ' + JSON.stringify(err, null, 2));
          return Observable.of({err: err, bungalows: null});
        })
        ;
    }

}

export interface BungalowsResponse {
  err: any;
  bungalows: Bungalow[];
}
export interface BungalowResponse {
  err: any;
  bungalow: Bungalow;
}

import { Injectable } from '@angular/core';
import { Http, Response} from '@angular/http';
import { environment as env } from '../../environments/environment';
import { Observable } from 'rxjs/Rx';
import { Bungalow } from './../model/bungalow';
import { BungalowSearch } from './../model/bungalow-search';

@Injectable()
export class BungalowService {

  constructor(private http: Http) { }

  public getAllBungalows(): Observable<BungalowsResponse> {
    return this.http.get(`${env.appUrl}/bungalows?requestid='1'`)
      .map(res => {
        const body: any = res.json();
        return { err: null, bungalows: body};
      })
      .catch(err => {
        console.log('Server error: ' + JSON.stringify(err, null, 2));
        return Observable.of({err: err, bungalows: null});
      })
      ;
    }

    public getBungalow(bungalowId: string): Observable<BungalowResponse> {
      return this.http.get(`${env.appUrl}/bungalows?requestid='2'&id='${bungalowId}''`)
        .map(res => {
          const body: any = res.json();
          return { err: null, bungalow: body};
        })
        .catch(err => {
          console.log('Server error: ' + JSON.stringify(err, null, 2));
          return Observable.of({err: err, bungalow: null});
        })
        ;
    }
    public getBungalows(bungalowsearch: BungalowSearch) : Observable<BungalowsSearchResponse> {
      return this.http.get(`${env.appUrl}/bungalows?requestid='3'&minbedcount=${bungalowsearch.minbedcount}&islandid=${bungalowsearch.islandid}&minprice=${bungalowsearch.minprice}&maxprice=${bungalowsearch.maxprice}&startweek=${bungalowsearch.startyear}${bungalowsearch.startweek}&endweek=${bungalowsearch.endyear}${bungalowsearch.endweek}`)
        .map(res => {
          const body: any = res.json();
          return { err: null, bungalows:JSON.stringify(body,null,1)};
          //return { err: null, bungalows: []};
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
export interface BungalowsSearchResponse {
  err: any;
  bungalows: Bungalow[];
}
export interface BungalowResponse {
  err: any;
  bungalow: Bungalow;
}

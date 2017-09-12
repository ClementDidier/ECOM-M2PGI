import { Injectable } from '@angular/core';
import { Http, Response} from '@angular/http';
import { environment as env } from '../../environments/environment';

@Injectable()
export class BungalowService {

  constructor(private http: Http) { }

  public all(): Observable<BungalowsResponse> {
    return this.http.get(`$env.appUrl}/bungalow`)
      .map(res => {
        const body: any = res.json();
        return { err: null, bungalows: body._embedded.bungalows};
      })
      .catch(err => {
        console.log('Server error: ' + JSON.stringify(err, null, 2));
        return Observable.of({err: err, todos: null});
      })
      ;
    }

    public get(bungalowId: string): Observable<BungalowResponse> {
      return this.http.get(`${env.serverUrl}/todos/${todoId}`)
        .map(res => {
          const body: any = res.json();
          console.log(JSON.stringify(body, null, 2));
          return { err: null, todo: body};
        })
        .catch(err => {
          console.log('Server error: ' + JSON.stringify(err, null, 2));
          return Observable.of({err: err, todo: null});
        })
        ;
    }
}

export interface BungalowsResponse {
  err: any;
  todos: Todo[];
}
export interface BungalowResponse {
  err: any;
  todo: Todo;
}

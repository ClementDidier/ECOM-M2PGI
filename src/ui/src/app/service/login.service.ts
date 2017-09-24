import { Injectable } from '@angular/core';
import { environment as env } from '../../environments/environment';
import { Observable } from 'rxjs/Rx';
import { Login } from './../model/login';

@Injectable()
export class LoginService {

constructor(private http: Http) { }

public getuserId(login: string,password: string): Observable<LoginResponse> {
  return this.http.get(`${env.appUrl}/connexion?login=${login}&password=${password}`)
    .map(res => {
      const body: any = res.json();
      return { err: null, login: body._embedded.login};
    })
    .catch(err => {
      console.log('Server error: ' + JSON.stringify(err, null, 2));
      return Observable.of({err: err, login: null});
    })
    ;
  }
}
export interface LoginResponse {
  err: any;
  login: Login[];
}

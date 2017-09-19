import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { Http, Response} from '@angular/http';
import { User } from './../model/user';
import { environment as env } from '../../environments/environment';

@Injectable()
export class UserService {

  constructor(private http : Http) { }

  public createUser(user: User): Observable<SignInResponse>
  {
      var formData = {
          'mail' : user.mail,
          'firstname' : user.firstname,
          'lastname' : user.lastname
      };

      return this.http.post(`${env.appUrl}/signin`, formData)
          .map(res => {
            const body: any = res.json();
            console.log(JSON.stringify(body, null, 2));
            return { err: null, msg: body };
          })
          .catch(err => {
            console.log('Server error: ' + JSON.stringify(err, null, 2));
            return Observable.of({err: err, msg: null});
        });
  }
}

export interface SignInResponse
{
  err: any;
  msg: string;
}

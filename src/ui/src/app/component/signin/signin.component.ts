import { Component, OnInit } from '@angular/core';
import { UserService } from './../../service/user.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { User } from '../../model/user';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit
{
    user : User;
    public location = '/signin';

    constructor(private userService : UserService, private router : Router) { }

    ngOnInit()
    {
        this.user = { 'mail' : '', 'firstname' : '', 'lastname': '' };
    }

    search()
    {
        this.userService.createUser(this.user).subscribe(res =>
        {
            if(!res.err) {
                console.log('OK - CREATE USER');
            }
            else console.log(res.err);
        })
    }

}

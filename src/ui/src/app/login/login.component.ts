import { Component, OnInit } from '@angular/core';
import { Router, Params } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  loginUser(e){
  e.preventDefault();
  var login = e.target.elements[0].value;
  var password = e.target.elements[1].value;
  console.log(login,password);
  return false;
  }
}

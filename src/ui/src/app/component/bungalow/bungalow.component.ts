import { Component,Input, OnInit } from '@angular/core';
import { Bungalow } from './../../model/bungalow';

@Component({
  selector: 'app-bungalow',
  templateUrl: './bungalow.component.html',
  styleUrls: ['./bungalow.component.css']
})
export class BungalowComponent implements OnInit {
  public location = '/bungalow';

  @Input() public bungalow: Bungalow;
  constructor() { }

  ngOnInit() {
  }

}

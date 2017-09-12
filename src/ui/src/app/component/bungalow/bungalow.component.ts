import { Component, OnInit } from '@angular/core';
import { Bungalow } from './../../model/bundalow';

@Component({
  selector: 'app-bungalow',
  templateUrl: './bungalow.component.html',
  styleUrls: ['./bungalow.component.css']
})
export class BungalowComponent implements OnInit {

  @Input() public bungalow: Bungalow;
  constructor() { }

  ngOnInit() {
  }

}

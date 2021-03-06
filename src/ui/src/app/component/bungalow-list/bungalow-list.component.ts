import { Component, OnInit } from '@angular/core';
import { Bungalow } from './../../model/bungalow';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-bungalow-list',
  templateUrl: './bungalow-list.component.html',
  styleUrls: ['./bungalow-list.component.css']
})
export class BungalowListComponent implements OnInit {
  public location = 'bungalowlist';

  bungalows : Bungalow[] = [];
  constructor(private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe((params : Params) =>
    {
        this.bungalows = JSON.parse(params['bungalows']);
    });
  }
}

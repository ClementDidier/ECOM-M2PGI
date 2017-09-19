import { Component, OnInit } from '@angular/core';
import { Bungalow } from './../../model/bungalow';
import { BungalowSearch } from './../../model/bungalow-search';

import { BungalowService } from './../../service/bungalow.service';
import { ActivatedRoute, Router, Params } from '@angular/router';

@Component({
  selector: 'app-bungalow-search',
  templateUrl: './bungalow-search.component.html',
  styleUrls: ['./bungalow-search.component.css']
})
export class BungalowSearchComponent implements OnInit {
  bungalowsearch: BungalowSearch;
  constructor(private bungalowService : BungalowService,
    private router: Router,
) { }

  getWeek(thisweek : Date) {
  let onejan = new Date(thisweek.getFullYear(),0,1);
  let today = new Date(thisweek.getFullYear(),thisweek.getMonth(),thisweek.getDate());
  let dayOfYear = (Math.abs(today.getTime() - onejan.getTime() +1)/86400000);
  return Math.ceil(dayOfYear/7)
};

  ngOnInit() {
    let thisweek = new Date();

    //TODO get list île et peupleur la recherche
    this.bungalowsearch={'minbedcount': 0, 'islandid' : 0, 'minprice':0,
                        'maxprice':500, 'startweek':this.getWeek(thisweek),'endweek':this.getWeek(thisweek)+1};
  }

  search(){
  this.bungalowService.getBungalows(this.bungalowsearch).subscribe(bungalowRes => {
        if(!bungalowRes.err){
            this.router.navigate(['/list', JSON.stringify(bungalowRes.bungalows)]);
    }
    else console.log(bungalowRes.err);
  })
  }
}

import { Component, OnInit } from '@angular/core';
import { Bungalow } from './../../model/bungalow';
import { BungalowService } from './../../service/bungalow.service';
import { ActivatedRoute, Router, Params } from '@angular/router';

@Component({
  selector: 'app-bungalow-search',
  templateUrl: './bungalow-search.component.html',
  styleUrls: ['./bungalow-search.component.css']
})
export class BungalowSearchComponent implements OnInit {
  bungalow: Bungalow;
  constructor(private bungalowService : BungalowService,
    private router: Router,
) { }

  ngOnInit() {
    this.bungalow={'nbLits': 0, 'ile' : 0, 'prixMax':0};
  }

  search(){
  this.bungalowService.getBungalows(this.bungalow).subscribe(bungalowRes => {
        if(!bungalowRes.err){
            this.router.navigate(['/list',bungalowRes.bungalows]);
    }
  })
  }
}

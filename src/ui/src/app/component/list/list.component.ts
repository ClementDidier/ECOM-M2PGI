import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { BungalowService } from './../../service/bungalow.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  constructor(private bungalowService : BungalowService,
    private router: Router,private activatedRoute: ActivatedRoute) {}
  ngOnInit() {
    this.bungalowService.all().subscribe(bungalowRes => {
          if(!bungalowRes.err){
              console.log(bungalowRes);
              this.router.navigate(['/bungalowlist', JSON.stringify(bungalowRes)]);
      }
      else console.log(bungalowRes.err);
  });
}

}

import { Component, OnInit } from '@angular/core';
import { Bungalow } from './../../model/bungalow';
import { BungalowListService } from '../../service/bungalow-list.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';

@Component({
  selector: 'app-bungalow-list',
  templateUrl: './bungalow-list.component.html',
  styleUrls: ['./bungalow-list.component.css']
})
export class BungalowListComponent implements OnInit {

  bungalows : Bungalow[] = [];
  constructor(private bungalowListService: BungalowListService, private router: Router) {
  }

  ngOnInit() {
	  
		this.bungalows = [
			{bungalowid: 1, bedcount: 2, islandid: 4, weekprice: 100},
			{bungalowid: 2, bedcount: 3, islandid: 4, weekprice: 150},
			{bungalowid: 3, bedcount: 4, islandid: 5, weekprice: 200}
	];
	  
	  
	  
	  
    //console.log(this.activatedRoute.params);
    //this.activatedRoute.params.subscribe((params : Params) =>
   // {
 //       this.bungalows = JSON.parse(params['bungalows']);
  //  });
  }
  
  sendCart(bungalowid, startweek, endweek){
	  let duration=0;
	  let parameter;
	  if(endweek>startweek){
		  duration=endweek-startweek;
		  ;
	  }else if(endweek<startweek){
		  duration=52-startweek+endweek;
	  }
	  
	  let durations=JSON.stringify(duration); 
	  this.bungalowListService.putCart("1", bungalowid, startweek, endweek, durations).subscribe(validRes =>
      {
        if(validRes.state== 1){
          this.router.navigate(['/cart', JSON.stringify(validRes)]);
        }
        else if(validRes.state==0){

        }
        else{
           console.log("Err : Ca n'est pas censé arriver");

        }

      }
    )
  }
	
	
	
	  
	  
	  
	  
  }


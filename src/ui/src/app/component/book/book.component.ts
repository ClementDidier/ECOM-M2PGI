import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Cart } from '../../model/cart';
import { BookService } from '../../service/book.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  cart : Cart;
  numCarte: number;
  constructor(private booktService : BookService, private activatedRoute: ActivatedRoute,  private router: Router) {
  }
  ngOnInit() {
    this.activatedRoute.params.subscribe((params : Params) =>
    {
      let cartitems = JSON.parse(params['cartitems']);
      let totalprice = 0;
      for(let cartItem of cartitems){
        totalprice += cartItem.bungalow.weekprice * cartItem.duration;
      }
        this.cart = { 'cartitems' : cartitems, 'totalprice' : totalprice};
    });
  }
  payment(){
    this.booktService.pay(this.numCarte).subscribe(payRes =>
      {
        if(payRes.state== 1){
          console.log("Youpi, y'a plus qu'a lui dire qu'on valide !")
        }
        else if(payRes.state==0){
          console.log("Paiement refusé")

        }
        else{
           console.log("Err : Ca n'est pas censé arriver");

        }

      });
  }
}

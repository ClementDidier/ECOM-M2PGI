import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Cart } from '../../model/cart';

@Component({
  selector: 'app-prebook',
  templateUrl: './prebook.component.html',
  styleUrls: ['./prebook.component.css']
})
export class PrebookComponent implements OnInit {

  cart : Cart;
  constructor(private activatedRoute: ActivatedRoute,  private router: Router) {
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

  gotToPayment (){
    this.router.navigate(['/book',JSON.stringify(this.cart)]);
  }
  Annuler(){
    this.router.navigate(['/']);

  }

}

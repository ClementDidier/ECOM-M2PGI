import { Component, OnInit } from '@angular/core';
import { CartService } from '../../service/cart.service';
import { Cart } from '../../model/cart';
import { CartItem } from '../../model/cart-item';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems : CartItem[];
  totalprice : number;
  constructor(private cartService : CartService) { }

  ngOnInit() {
    this.cartService.getCart().subscribe(cartRes => {
          if(!cartRes.err){
            this.cartItems = cartRes.cartitems;
            this.totalprice = 0;
            for(let cartItem of this.cartItems){
              this.totalprice += cartItem.bungalow.weekprice * cartItem.duration;
            }
      }
      else console.log("Err : " + cartRes.err);
    });
  }
  validate(){
    console.log("bisous");
  }

}

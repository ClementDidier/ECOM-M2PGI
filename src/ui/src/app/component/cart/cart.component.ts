import { Component, OnInit } from '@angular/core';
import { CartService } from '../../service/cart.service';
import { Cart } from '../../model/cart';
import { CartItem } from '../../model/cart-item';
import { ActivatedRoute, Router, Params } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems : CartItem[];
  totalprice : number;
  constructor(private cartService : CartService, private router: Router) { }

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
    this.cartService.validateCart().subscribe(validRes =>
      {
        if(validRes.state== 1){
          this.router.navigate(['/prebook', JSON.stringify(validRes)]);
        }
        else if(validRes.state==0){

        }
        else{
           console.log("Err : Ca n'est pas censé arriver");

        }

      }
    )  }
  emptyCart(){
    this.cartService.emptyCart().subscribe(validRes =>
      {
        if(validRes.state["state"]== 1){
          console.log("la vie est belle");/*
          this.cartService.getCart().subscribe(cartRes => {
                if(!cartRes.err){
                  this.cartItems = cartRes.cartitems;
                  this.totalprice = 0;
                  for(let cartItem of this.cartItems){
                    this.totalprice += cartItem.bungalow.weekprice * cartItem.duration;
                  }
            }
            else console.log("Err : " + cartRes.err);
          });*/
          //this.router.reload();
          this.cartService.getCart();
          window.location.reload();
       }
        else if(validRes.state["state"]==0){
          console.log("la vie est pas belle")

        }
        else{
           console.log("Err : Ca n'est pas censé arriver CRISS : " + JSON.stringify(validRes.state));

        }

      }
    )
  }

}

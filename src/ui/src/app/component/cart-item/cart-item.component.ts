import { Component,Input, OnInit } from '@angular/core';
import { CartItem } from '../../model/cart-item';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent implements OnInit {

  @Input() public cartItem: CartItem;

  constructor() { }

  ngOnInit() {
  }

}

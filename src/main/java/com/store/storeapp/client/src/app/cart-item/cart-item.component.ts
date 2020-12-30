import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent implements OnInit {
  @Input() cartItem: any; 
  @Input() isRemovable : boolean;
  @Output() removeRequest = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
  }

  removeItem(cartItemId : string) : void {
    this.removeRequest.emit(cartItemId);
  }
}

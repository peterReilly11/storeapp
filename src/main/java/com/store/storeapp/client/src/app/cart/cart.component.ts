import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { CartService } from '../shared/cart/cart.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit, OnDestroy {

  cart : any;
  isCartItemRemovable : boolean;
  cartId : string;
  sub: Subscription;

  constructor(private cartService : CartService,
              private route: ActivatedRoute,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    if(this.cart === undefined){
      this.sub = this.route.params.subscribe(params => {
        this.cartId = params.id;
        this.getCartItems();
      },
      error => {
        console.log("error ngOnInit");
        console.log(error);
      });
    } else{
      this.setRemovableFlag();
    }
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  removeItem(cartItemId : string) : void {
    this.cartService.removeItem(cartItemId).subscribe(data => {
      this.getCartItems();
    });
  }

  getCartItems() : void{
    this.cartService.getCart().subscribe(data => { 
      this.cart = data;
      this.setRemovableFlag();
    },
    error => {
      console.log("error getCartItems");
      console.log(error);
    }
    );
  }

  checkout() : void {
    if(this.cart.cartItems.length > 0){
      this.cart.orderPlaced = true;
      this.cartService.checkout(this.cart).subscribe(data => {
        this.cart = data;
        let snackBarRef = this.snackBar.open('Checkout has been placed', '', {duration: 3000});
      },
      error => {
        console.log("error checkout");
        console.log(error);
      });
    } else {
      let snackBarRef = this.snackBar.open('Please add items before checking out', '', {duration: 3000});
    }
  }

  setRemovableFlag() : void {
    if(this.cart.orderPlaced){
      this.isCartItemRemovable = false;
    } else {
      this.isCartItemRemovable = true;
    }
  }

}

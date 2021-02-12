import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { CartItemComponent } from '../../cart-item/cart-item.component';
import { CartComponent } from '../../cart/cart.component';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private apiBase = '//localhost:8080';
  private cartURL = this.apiBase + '/cart';
  private cartItemURL = this.apiBase + '/cart-item';

  constructor(private http : HttpClient) {
  }

  createCart(): Observable<any>{
    return this.http.get(this.cartURL);
  }

  getAllCarts() : Observable<any>{
    return this.http.get(this.apiBase + "/all-carts");
  }

  getAllMyCarts() : Observable<any>{
    return this.http.get(this.apiBase + "/all-my-carts");
  }

  getCart() : Observable<any>{
    return this.http.get(this.cartURL);
  }

  addItem(cartItem : any) : Observable<any>{
    let result : Observable<any>;
    if (cartItem.href) {
      result = this.http.put(cartItem.href, cartItem);
    } else {
      result = this.http.post(this.cartItemURL, cartItem);
    }
    return result;
  }

  addCartItem(newCartItem : any) : void{
    this.getCart().subscribe(data => { 
      data.cartItems.push(newCartItem);
      this.updateCart(data).subscribe(data2 => {
      },
      error => {
        console.log("error addCartItem");
        console.log(error);
      });
    });
  }

  removeItem(cartItemId : string) : Observable<{}>{
    const url = this.cartItemURL + "/" + cartItemId;
    return this.http.delete(url);
  }

  updateCart(cart : any) : Observable<any>{
    return this.http.put(this.cartURL, cart);
  }

  checkout(cart : any) : Observable<any>{
    return this.http.put(this.apiBase + '/cart-checkout', cart);
  }
}
  
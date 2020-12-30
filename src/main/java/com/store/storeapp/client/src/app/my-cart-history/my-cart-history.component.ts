import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartService } from '../shared/cart/cart.service';

@Component({
  selector: 'app-my-cart-history',
  templateUrl: './my-cart-history.component.html',
  styleUrls: ['./my-cart-history.component.css']
})
export class MyCartHistoryComponent implements OnInit {

  carts : any;

  constructor(private cartService : CartService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getAllMyCarts();
  }

  getAllMyCarts() : void {
    this.cartService.getAllMyCarts().subscribe(data => { 
      console.log("data");
      console.log(data);
      this.carts = data._embedded.carts;
    },
    error => {
      console.log("error getAllMyCarts");
      console.log(error);
    }
    );
  }

}

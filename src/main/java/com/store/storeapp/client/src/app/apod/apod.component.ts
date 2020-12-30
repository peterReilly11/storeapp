import { Injectable } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { DomSanitizer } from '@angular/platform-browser';
import { CartComponent } from '../cart/cart.component';
import { CartService } from '../shared/cart/cart.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ApiInfoModule } from '../api-info/api-info.module';

@Component({
  selector: 'app-apod',
  templateUrl: './apod.component.html',
  styleUrls: ['./apod.component.css']
})
@Injectable({providedIn: 'root'})
export class ApodComponent implements OnInit {

  constructor(private http : HttpClient, 
              private sanitizer: DomSanitizer, 
              private cartService : CartService,
              private snackBar: MatSnackBar) { }

  private apiKey = ApiInfoModule.nasaAPIKey;
  baseURL = 'https://api.nasa.gov/planetary/apod/?api_key='+this.apiKey;
  response : any;
  youtubeString : any;

  ngOnInit(): void {
  }

  selectDate(event: MatDatepickerInputEvent<Date>) {
    console.log(event.value);
    console.log(event.value.getMonth());
    const dateToSend = '&date=' + event.value.getUTCFullYear() + '-' + (event.value.getMonth() + 1) + '-' + event.value.getDate();
    const url = this.baseURL + dateToSend;
    console.log(url);
    const myObserver = {
      next: response => this.response = response,
      error: err => console.error('Observer got an error: ' + err),
      complete: () => {
        if(this.response.media_type === 'video' && this.response.url.includes('youtube')){
          this.youtubeString = this.sanitizer.bypassSecurityTrustResourceUrl(this.response.url);
        }
      }
    };
    this.http.get(url).subscribe(myObserver);
  }

  addToCart() : void{
    if(this.response){
      const newCartItem = {
        itemName : this.response.title,
        itemDescription : this.response.explanation.substring(0,200),//this.response.explanation,
        itemURL : this.response.url,
        dateTimeCreated : Date.now()
      };
      this.cartService.addCartItem(newCartItem);
      let snackBarRef = this.snackBar.open('Item has been added to cart', '', {duration: 3000});
    } else {
      let snackBarRef = this.snackBar.open('Please select a date before adding to cart', '', {duration: 3000});
    }
  }
}

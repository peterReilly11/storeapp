import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ApiInfoModule } from '../api-info/api-info.module';

@Component({
  selector: 'app-donki',
  templateUrl: './donki.component.html',
  styleUrls: ['./donki.component.css']
})
export class DonkiComponent implements OnInit {

  constructor(private http : HttpClient) { }
  
  private apiKey = ApiInfoModule.nasaAPIKey;
  baseURL = 'https://api.nasa.gov/DONKI/';

  ngOnInit(): void {
  }

}

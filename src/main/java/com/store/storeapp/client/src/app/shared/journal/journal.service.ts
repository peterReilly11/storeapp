import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JournalService {
  private localHost = '//localhost:8080';

  constructor(private http : HttpClient) { }

  getJournalsByEmail() : Observable<any>{
    return this.http.get(this.localHost + '/journals-by-email');
  }

  putJournal(journal : any){
    this.http.put(this.localHost + '/journal', journal).subscribe(data => {
    },
    error => {
      console.log("error put journal");
      console.log(error);
    });;
  }

  getAllJournals() : Observable<any>{
    return this.http.get(this.localHost + '/all-journals');
  }

  postJournal(journal : any) {
    this.http.post(this.localHost + '/journal', journal).subscribe(data => {
    },
    error => {
      console.log("error post journal");
      console.log(error);
    });;
  }
}
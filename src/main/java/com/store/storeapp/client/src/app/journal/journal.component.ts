import { Component, Inject, OnInit } from '@angular/core';
import { dateInputsHaveChanged } from '@angular/material/datepicker/datepicker-input-base';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { JournalService } from '../shared/journal/journal.service';
import { ActivatedRoute, Router } from '@angular/router';

export interface JournalData {
  id : string;
  description : string;
  dateTimeCreated : Date;
  entry : string;
}

@Component({
  selector: 'app-journal',
  templateUrl: './journal.component.html',
  styleUrls: ['./journal.component.css']
})
export class JournalComponent implements OnInit {

  blankJournal = {description : '', dateTimeCreated : new Date(), entry : ''};
  journal = {...this.blankJournal};
  journals = [];
  allJournals = [];
  initialDBCallout = false;
  sub: Subscription;

  constructor(public dialog: MatDialog,
              private journalService : JournalService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    if(this.initialDBCallout === false){
      this.journalService.getJournalsByEmail().subscribe(result => {
        if(result._embedded){
          if(result._embedded.journals){
            this.journals = [...result._embedded.journals];
          }
        }
      },
      error => {
        console.log("error ngOnInit");
        console.log(error);
      });

      this.journalService.getAllJournals().subscribe(result => {
        if(result._embedded){
          if(result._embedded.journals){
            this.allJournals = [...result._embedded.journals];
          }
        }
      },
      error => {
        console.log("error ngOnInit");
        console.log(error);
      });

      this.initialDBCallout = true;
    }
  }

  openDialog(): void {    
    const dialogRef = this.dialog.open(JournalModal, {
      width: '40%',
      data : this.journal
    })

    dialogRef.afterClosed().subscribe(result => {
      if(result != undefined){
        this.journalService.postJournal(result);
        this.journals.push({...result});
        this.journal = this.blankJournal;
      }
    });
  }

  openDialagEdit(passedJournal : JournalData) : void {    
    const dialogRef = this.dialog.open(JournalModal, {
      width: '40%',
      data : passedJournal
    })

    dialogRef.afterClosed().subscribe(result => {
      if(result != undefined){
        this.journalService.putJournal(result);
        this.journal = this.blankJournal;
      }
    });
  }

}


@Component({
  selector: 'new-journal-modal',
  templateUrl: './new-journal-modal.html',
})
export class JournalModal {

  constructor(
    public dialogRef: MatDialogRef<JournalModal>,
    @Inject(MAT_DIALOG_DATA) public data: JournalData ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Card } from 'src/app/models/card.model';
import { Section } from 'src/app/models/section.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-card1',
  templateUrl: './card1.component.html',
  styleUrls: ['./card1.component.css']
})
export class Card1Component implements OnInit {

  @Input() column!: string;
  @Input() card!: Card;
  @Output() cardChanged = new EventEmitter<Card[]>();


  cols = Section;
  atFirstColumn?: boolean;
  atLastColumn?: boolean;

  id: string = '';
  title: string = '';
  description: string = '';
  lista: string = '';


  editMode: boolean = false;

  constructor( private auth: AuthService) {

  }

  ngOnInit(): void {
    this.getCardInfo()
  }

  getCardInfo(){
    this.atFirstColumn = this.cols.indexOf(this.card.list) == 0;
    this.atLastColumn = this.cols.indexOf(this.card.list) == this.cols.length - 1;
    this.id = this.card.id;
    this.title = this.card.title;
    this.description = this.card.content;
    this.lista = this.card.list;

    if (!this.id){
      this.toggleEditMode();
    }
  };

  toggleEditMode() {
    this.editMode = !this.editMode;
  };

  moveCardRight(){    
    if(this.atLastColumn){
      return;
    } else if(this.card.list == 'Doing'){
      this.atLastColumn = true
    }
    


    const index = this.cols.indexOf(this.card.list);
    this.card.list = this.cols[index + 1];
    this.auth.editCard(this.card.id, this.title, this.description, this.card.list).subscribe((card) => {
      this.auth.changeCards.next(card);
    });
    console.log(this.card.id);
  }

  moveCardLeft(){
    if(this.atFirstColumn){
      return;
    }

    const index = this.cols.indexOf(this.card.list);
    this.card.list = this.cols[index - 1];
    this.auth.editCard(this.card.id, this.title, this.description, this.card.list).subscribe((card) => {
      this.auth.changeCards.next(card);
    });
    console.log(this.card.id);
  }

  save(){
    if(this.card.id){
      this.auth.editCard(this.card.id, this.title, this.description, this.card.list).subscribe((card) =>{
        
      });
    } else {
      this.auth.createCard(this.title, this.description, this.lista)
      .subscribe((card) => {
        this.auth.changeCards.next(card);
      });
    }

    this.toggleEditMode();
  }

  cancel() {
    this.title = this.card.title;
    this.description = this.card.content;
    this.lista = this.card.list;
    this.toggleEditMode();
  }

  deleteCard() {
    this.auth.deleteCard(this.card.id).subscribe((card) => {
      this.auth.changeCards.next(card);
    });
  }
}

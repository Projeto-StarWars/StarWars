import { Component, Input, OnInit } from '@angular/core';
import { Card } from 'src/app/models/card.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {

  @Input() column!: string;
  @Input() cards!: Card[];

  constructor(private auth: AuthService) {

  }

  ngOnInit(): void {
  }

  onCardChanged(x: Card) {
    this.cards.forEach((card) => {
      if (card.id === x.id) {
        card.content = x.content;
        card.title = x.title;
        card.title = x.title;
      }
    })
    
  }

  createNewCard() {
    this.cards.push({ title: '', content: '', list: this.column, id: '' });
  }

}

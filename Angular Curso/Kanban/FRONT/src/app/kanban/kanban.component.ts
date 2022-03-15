import { Component, OnInit } from '@angular/core';
import { Section } from '../models/section.model';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { Card } from 'src/app/models/card.model';


@Component({
  selector: 'app-kanban',
  templateUrl: './kanban.component.html',
  styleUrls: ['./kanban.component.css'],
})
export class KanbanComponent implements OnInit {
  columns = Section;
  logedIn!: boolean;

  cards!: Card[];
  constructor(private auth: AuthService, private router: Router) {}

  ngOnInit(): void {
     this.getAllCardsFromAPI();

     this.auth.changeCards.subscribe((card) => {
       this.getAllCardsFromAPI();
     });
  }

  getAllCardsFromAPI() {
    this.auth.getCards().subscribe((cards) => {
      if (!cards) {
        return;
      } else {
        this.cards = cards;
      }
    });
  }
}

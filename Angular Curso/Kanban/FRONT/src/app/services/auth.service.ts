import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
import { Card } from '../models/card.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

    redirect_uri = "http://0.0.0.0:5000" 

    constructor(private httpClient: HttpClient) {}

    cards!: Card[];
    authorization: string = localStorage.getItem('auth') || '';
    headers = {
        'Content-Type': 'application/json',
        Authorization: this.authorization,
    };

    changeCards = new Subject();
    isLogged = new Subject();

    getToken(login: string, senha: string){
        const url = this.redirect_uri + '/login/';
        const msgBody = { login: login, senha: senha };
        const headers = { 'Content-Type': 'application/json' };
        const options = { headers: headers }; 
        return this.httpClient.post<string>(url, msgBody, options);
    }

    setLocal(auth: string){
        this.authorization = auth;
        localStorage.setItem('auth', this.authorization);
        this.isLogged.next(true);
    }

    clearLocal(){
        this.authorization = '';
        localStorage.removeItem('auth');
        this.isLogged.next(true);
    }

    getCards(){
        const url = this.redirect_uri + '/cards/';
        const options = { headers: this.headers};
        return this.httpClient.get<Card[]>(url, options);
    }

    createCard(titulo: string, content: string, lista: string) {
        let card = new Card(titulo, content, lista, '');
        const url = this.redirect_uri + '/cards/';
        const options = { headers: this.headers};
        return this.httpClient.post<Card[]>(url, card, options);
    }

    editCard(id: string, title: string, content: string, list: string){
        const url = this.redirect_uri + '/cards/' + id;
        const options = { headers: this.headers };
        return this.httpClient.put<Card[]>(url, {title, content, list}, options);
    }

    deleteCard(id: string){
        const url = this.redirect_uri + '/cards/' + id;
        const options = { headers: this.headers};
        return this.httpClient.delete(url, options);
    }
}
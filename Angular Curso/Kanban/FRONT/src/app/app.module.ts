import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';

import { AuthService } from './services/auth.service';
import { AppRoutingModule } from './app-routing.module';
import { KanbanComponent } from './kanban/kanban.component';
import { SectionComponent } from './kanban/section/section.component';
import { Card1Component } from './kanban/section/card1/card1.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    KanbanComponent,
    SectionComponent,
    Card1Component
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }

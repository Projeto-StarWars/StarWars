import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  loginError: boolean = false;

  constructor( private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup(
      {
        login: new FormControl(null),
        senha: new FormControl(null),
      }
    )
  }

  login(){
    this.auth.getToken(this.loginForm.value.login, this.loginForm.value.senha).subscribe((tokenApi) =>{
      if (tokenApi) {
        this.auth.setLocal(tokenApi);
        this.router.navigateByUrl('/kanban');
        this.loginError = false;
        console.log(tokenApi)
      } else {
        this.loginError = true;
        this.auth.clearLocal();
        this.loginForm.reset(); //reseta os valores colocados nos inputs
      }
    })
  }

  logout(){
    this.router.navigateByUrl('/login');
    this.auth.clearLocal();
  }
}

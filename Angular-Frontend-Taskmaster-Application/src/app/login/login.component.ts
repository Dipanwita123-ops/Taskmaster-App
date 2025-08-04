import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HardcodedAuthenticationService } from '../service/hardcoded-authentication.service';
import { BasicAuthenticationService } from '../service/basic-authentication.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

username =''
password = ''
errorMessage = 'Invalid Credentials'
invalidLogin= false

//Router
// Dependency Injection
//in angular , DI is a built-in feature 
// we need router for login .so router is Dependency for login
  constructor(private router:Router ,
    private hardcodedAuthenticationService:HardcodedAuthenticationService ,
  private basicAuthenticationService:BasicAuthenticationService)
 {} // this is how Dependency Injection is done by defining it
 //  i.e the private hardcodedAuthenticationService:HardcodedAuthenticationService
 //inside the connstructor.
  

  ngOnInit() {
  }

//   //hardcoded
// handleLogin() {
//   //console.log(this.username);
// //if(this.username==="Dipanwita" && this.password ==='dummy'){
 
// if(this.hardcodedAuthenticationService.authenticate(this.username ,this.password)){

//   //Redirect to welcome page using navigate()
//   this.router.navigate(['welcome', this.username]);


  
//   this.invalidLogin =false
// } else{
//   this.invalidLogin =true
// }
   
// }

handleJWTAuthLogin() {
 
 
(this.basicAuthenticationService.executeJWTAuthenticationService(this.username ,this.password))
  .subscribe(

    data => {
       
      console.log(data)
      this.router.navigate(['welcome'])
      this.invalidLogin =false

    },

    error => {
          console.log(error)
          this.invalidLogin =true
    }
  )
 
} 




handleBasicAuthLogin() {
 
 
(this.basicAuthenticationService.executeAuthenticationService(this.username ,this.password))
  .subscribe(

    data => {
       
      console.log(data)
      this.router.navigate(['welcome'])
      this.invalidLogin =false

    },

    error => {
          console.log(error)
          this.invalidLogin =true
    }
  )
 
} 
   
}



import { Injectable } from '@angular/core';

// @Injectable({providedIn: 'root'}-It tells Angular:
//  "Create one shared instance (singleton) of this service, and make it available app-wide
// to be used using Dependency Injection 
@Injectable({
  providedIn: 'root'
})
export class HardcodedAuthenticationService {

  constructor() { }

  authenticate(username ,password){
//console.log('before '+ this.isUserLoggedIn());

    if(username==="Dipanwita" && password ==='dummy'){

      sessionStorage.setItem('authenticatedUser',username);

    // console.log('after '+ this.isUserLoggedIn()); 
      return true;
    }
    return false;
  }


isUserLoggedIn(){

  let user = sessionStorage.getItem('authenticatedUser') 
  return !(user===null)

}

logout(){
  sessionStorage.removeItem('authenticatedUser')
}
}

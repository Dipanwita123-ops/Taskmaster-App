import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {map} from 'rxjs/operators';

// @Injectable({providedIn: 'root'}-It tells Angular:
//  "Create one shared instance (singleton) of this service, and make it available app-wide
// to be used using Dependency Injection 

export const TOKEN = 'token'
export const AUTHENTICATED_USER ='authenticatedUser'


@Injectable({
  providedIn: 'root'
})
export class BasicAuthenticationService {

  constructor(private http : HttpClient) { }

  
executeJWTAuthenticationService(username , password){
  
              
  
     return this.http.post<any>(`http://localhost:8080/authenticate` ,
          {
           username,
           password   

          }).pipe(

map(

  data =>{

    sessionStorage.setItem(AUTHENTICATED_USER , username);
    sessionStorage.setItem(TOKEN , `Bearer ${data.token}`);
    return data;
  }


)

      );
      
     //used backtick(``)In TypeScript/JavaScript, to use template literals (i.e., ${} syntax for variable interpolation), you need to use backticks (`)
     //With backticks, ${name} will be replaced with the actual variable value.
    }
  






  executeAuthenticationService(username , password){
  
              
   let basicAuthHeaderString  = 'Basic ' + window.btoa(username + ':' +password);
           
  
  let headers = new HttpHeaders(
    {
      Authorization  : basicAuthHeaderString
    }
  );
  
     return this.http.get<AuthenticationBean>(`http://localhost:8080/basicauth` ,
          {headers,
          withCredentials: true
      }).pipe(

map(

  data =>{

    sessionStorage.setItem(AUTHENTICATED_USER , username);
    sessionStorage.setItem(TOKEN , basicAuthHeaderString);
    return data;
  }


)

      );
      
     //used backtick(``)In TypeScript/JavaScript, to use template literals (i.e., ${} syntax for variable interpolation), you need to use backticks (`)
     //With backticks, ${name} will be replaced with the actual variable value.
    }
  
getAuthenticatedUser(){

 return sessionStorage.getItem(AUTHENTICATED_USER) 
 

}

getAuthenticatedToken(){

  if(this.getAuthenticatedUser())
 return sessionStorage.getItem(TOKEN) 
 

}


isUserLoggedIn(){

  let user = sessionStorage.getItem(AUTHENTICATED_USER) 
  return !(user===null)

}

logout(){
  sessionStorage.removeItem(AUTHENTICATED_USER)
  sessionStorage.removeItem(TOKEN)
}
}

export class  AuthenticationBean{
  constructor(public message :string){

  }
}

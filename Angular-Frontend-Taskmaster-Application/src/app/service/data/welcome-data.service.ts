import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})




export class WelcomeDataService {

  constructor(
    private http : HttpClient) { }

  executeHelloWorldBeanService(){

   return this.http.get('http://localhost:8080/hello-world-bean');
  }

//http://localhost:8080/hello-world/path-variable/Dipanwita

executeHelloWorldBeanServiceWithPathVariable(name){

// let basicAuthHeaderString = this.createBasicAuthenticationHttpHeader();

// let headers = new HttpHeaders(
//   {
//     Authorization  : basicAuthHeaderString
//   }
// );

   return this.http.get(`http://localhost:8080/hello-world/path-variable` ,
       
      // {headers,
      //   withCredentials: true
      //    }
  );
    
   //used backtick(``)In TypeScript/JavaScript, to use template literals (i.e., ${} syntax for variable interpolation), you need to use backticks (`)
   //With backticks, ${name} will be replaced with the actual variable value.
  }

  // createBasicAuthenticationHttpHeader(){
  //   let username ='Dipanwita'
  //   let password = 'dummy'
  //   let basicAuthHeaderString  = 'Basic ' + window.btoa(username + ':' +password);
  //   return basicAuthHeaderString;
  // }

  //Access to XMLHttpRequest at 'http://localhost:8080/hello-world/path-variable/Dipanwita' from origin 'http://localhost:4200' 
  // has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.

}

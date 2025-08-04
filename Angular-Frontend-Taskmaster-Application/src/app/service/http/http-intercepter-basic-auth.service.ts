import { HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BasicAuthenticationService } from '../basic-authentication.service';

@Injectable({
  providedIn: 'root'
})
export class HttpIntercepterBasicAuthService implements HttpInterceptor{

  constructor(
    private basicAuthenticationService : BasicAuthenticationService
  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler){

      // let username ='Dipanwita'
      // let password = 'dummy'
      //let basicAuthHeaderString  = 'Basic ' + window.btoa(username + ':' +password);

     let basicAuthHeaderString = this.basicAuthenticationService.getAuthenticatedToken();
     let username = this.basicAuthenticationService.getAuthenticatedUser()

     if(basicAuthHeaderString && username) {
                  
                    request = request.clone(

                {
                        setHeaders : {

                          Authorization : basicAuthHeaderString
                        },
                          withCredentials: true
                        })
              
 
  }

 console.log('➡️ Intercepting request to:', request.url, 'Authorization:', request.headers.get('Authorization'));
     

return next.handle(request);
  }

}

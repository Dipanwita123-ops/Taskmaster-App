import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { HardcodedAuthenticationService } from '../service/hardcoded-authentication.service';


@Injectable({
  providedIn: 'root'
})
export class RouteGaurdService implements CanActivate {

  constructor(private hardcodedAuthenticationService:HardcodedAuthenticationService,
               private router : Router )
  
   { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot){

    if(this.hardcodedAuthenticationService.isUserLoggedIn())
       return true;

// If user tries to acces any other page eg todos ,welcome ,logout then redirecting back to login page 
//its done using DI (private router : Router) then using router object to call navigate()...

this.router.navigate(['login']) ;


       return false;
  }
}

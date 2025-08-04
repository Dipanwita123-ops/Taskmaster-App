import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WelcomeDataService } from '../service/data/welcome-data.service';
import { BasicAuthenticationService } from '../service/basic-authentication.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {


  name: string = '';
  welcomeMessageFromService: string = '';
  errorMessageFromService: string = '';
  messageType: 'success' | 'error' | '' = '';

  //ActivatedRoute
  constructor(
    private route : ActivatedRoute,
    private service : WelcomeDataService,
    private basicAuthenticationService: BasicAuthenticationService) { }

  ngOnInit() {

    //console.log(this.message)
   // console.log(this.route.snapshot.params['name'])
//this.name = this.route.snapshot.params['name'] ;
this.name = this.basicAuthenticationService.getAuthenticatedUser();
  }

getWelcomeMessage() {
  this.welcomeMessageFromService = '';
    this.errorMessageFromService = '';
    this.messageType = '';

  this.service.executeHelloWorldBeanService().subscribe(
    response => this.handleSuccessfulResponse(response),
    error => this.handleErrorResponse(error)
  );
}

getWelcomeMessageWithParameter() {
  this.welcomeMessageFromService = '';
    this.errorMessageFromService = '';
    this.messageType = '';

  this.service.executeHelloWorldBeanServiceWithPathVariable(this.name).subscribe(
    response => this.handleSuccessfulResponse(response),
    error => this.handleErrorResponse(error)
  );
}

handleSuccessfulResponse(response: any) {
    this.welcomeMessageFromService = response.message;
    this.messageType = 'success';
  }

handleErrorResponse(error: any) {
    if (error.error && error.error.message) {
      this.errorMessageFromService = error.error.message;
    } else {
      this.errorMessageFromService = 'An unexpected error occurred. ';
    }
     this.messageType = 'error';
  }
 
}

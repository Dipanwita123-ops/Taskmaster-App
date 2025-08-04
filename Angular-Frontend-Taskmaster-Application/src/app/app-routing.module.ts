import { ErrorHandler, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { ErrorComponent } from './error/error.component';
import { ListTodosComponent } from './list-todos/list-todos.component';
import { LogoutComponent } from './logout/logout.component';
import { RouteGaurdService } from './servive/route-gaurd.service';
import { TodoComponent } from './todo/todo.component';

const routes: Routes = [
 {path:'' , component : LoginComponent},//  Default route
{path:'login' , component : LoginComponent}, // /login path
{path:'welcome' , component : WelcomeComponent , canActivate:[RouteGaurdService]},// /welcome path
{path:'todos' , component : ListTodosComponent , canActivate:[RouteGaurdService]},
{path:'logout' , component : LogoutComponent , canActivate:[RouteGaurdService]},
{path:'todos/:Id' , component : TodoComponent , canActivate:[RouteGaurdService]},

{path:'**' , component : ErrorComponent}// '**' is a  regular expression which matches to anything
// when user types invalid url Angular will fallback to Errorcomponent
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

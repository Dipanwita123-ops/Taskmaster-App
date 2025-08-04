import { Component, OnInit } from '@angular/core';
import { TodoDataService } from '../service/data/todo-data.service';
import { Route, Router } from '@angular/router';
import { BasicAuthenticationService } from '../service/basic-authentication.service';

export class Todo {
constructor(

  public Id:number,
  public Description:string,
  public Done:boolean,
  public TargetDate:Date

)
{

}

}



@Component({
  selector: 'app-list-todos',
  templateUrl: './list-todos.component.html',
  styleUrls: ['./list-todos.component.css']
})
export class ListTodosComponent implements OnInit {



//   //creating a  single todo object in JavaScript
//  todo = {
// id : 1 ,
// description : 'Learn to Dance'

//   }

  //creating a list of todos
todos :Todo[]
message : string
username : string
// [
// new Todo(1 ,'Learn To Dance',false ,new Date()),
// new Todo(2 ,'Become  Angular Expert',false ,new Date()),
// new Todo(3 ,'Write Journal',true ,new Date())

// // {Id : 1 , Description : 'Learn To Dance'},
// // {Id : 2 , Description : 'Become  Angular Expert'},
// // {Id : 3 , Description : 'Write Journal'}





// ]



  constructor(private todoService:TodoDataService,
              private router : Router,
              private basicAuthenticationService: BasicAuthenticationService
  ) { }

  ngOnInit() {
this.username = this.basicAuthenticationService.getAuthenticatedUser();
this.refreshTodos();
  }

  refreshTodos(){
    this.todoService.retrieveAllTodos().subscribe(

response =>{

  console.log(response);
  this.todos = response;
}

)
  }

deleteTodo(Id){
  console.log(`delete todo ${Id}`)
  this.todoService.deleteTodo( Id).subscribe(
    response =>{
      console.log(response);
      this.message =`Delete of Todo ${Id} is Succesfull !`
      this.refreshTodos();
    }
  )
}

updateTodo(Id){
  console.log(`update ${Id}`)
  this.router.navigate(['todos',Id])
}

addTodo(){
  this.router.navigate(['todos' , -1])
}

}

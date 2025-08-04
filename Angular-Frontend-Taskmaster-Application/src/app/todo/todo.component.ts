import { Component, OnInit } from '@angular/core';
import { TodoDataService } from '../service/data/todo-data.service';
import { Todo } from '../list-todos/list-todos.component';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { BasicAuthenticationService } from '../service/basic-authentication.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  Id:number
  todo:Todo
  username : string
  
  constructor(
private todoService : TodoDataService,
private route : ActivatedRoute,
private router : Router,
private basicAuthenticationService: BasicAuthenticationService
  ) { }

  

  ngOnInit() {

 this.Id = this.route.snapshot.params['Id'] ;
this.todo = new Todo(this.Id,'',false,new Date());

this.username = this.basicAuthenticationService.getAuthenticatedUser();

if(this.Id != -1){
    this.todoService.retrieveTodo(this.Id)
        .subscribe(

          data=> this.todo = data

    )


  }
}

  saveTodo(){
 if(this.Id == -1){

  delete this.todo.Id;                   // ✅ remove the -1 so backend treats it as NEW



  //create Todo
     this.todoService.createTodo(this.todo).subscribe(

          data => {
            console.log(data)
            this.router.navigate(['todos'])
      }
    )
 }
    else{
       this.todoService.updateTodo(this.Id, this.todo).subscribe(

          data => {
            console.log(data)
            this.router.navigate(['todos'])
      }
    )
  }
  }

  submitted = false;

onSubmit(form: NgForm) {
  this.submitted = true;

  if (form.invalid) {
    // Don't submit, show alert
    return;
  }

  // Proceed with saving if form is valid
  this.saveTodo();
}


}

import { Component, OnInit } from '@angular/core';
import { UserService } from '../shared/user.service';
import { CreateUserDTO } from '../shared/create-user-dto.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  dto = new CreateUserDTO();

  constructor(private service: UserService) { }

  ngOnInit() {
  }

  onSubmit() {
    this.service.create(this.dto).subscribe(dto => {
      console.log(dto);
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { AadharService } from '../aadhar.service';
import { Citizen } from '../classes';
import { Router } from '@angular/router';

@Component({
  selector: 'app-citizen',
  templateUrl: './citizen.component.html',
  styleUrls: ['./citizen.component.css']
})
export class CitizenComponent implements OnInit{
constructor(private service:AadharService,private router:Router){}
message='';
aadharStatus='';
citizen:Citizen=new Citizen();
showDashboard = false;
ngOnInit(): void {
  
}

public loginAsCitizen() {
  this.service.checkCitizenLoginCreds(this.citizen.emailId, this.citizen.mobileNo).subscribe(
    respose => {
      console.log(respose);

      if (respose == '1') {
        this.service.getCitizen(this.citizen.emailId).subscribe(response2 => this.citizen = response2)
        this.showDashboard = true;

      }
      else {
        this.message = "Invalid Credentials!";
      }
    }
  )
}
public logout(){
  this.service.citizenLogout(this.citizen.emailId).subscribe();
  this.router.navigate(['']);
}
public getAadharStatus(emailId:string,password:string){
  this.service.getAadharStatus(emailId,password).subscribe(response=>this.aadharStatus=response)
  console.log(this.aadharStatus);
  
}

}

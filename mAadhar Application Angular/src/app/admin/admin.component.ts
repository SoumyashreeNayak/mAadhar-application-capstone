import { Component, OnInit } from '@angular/core';
import { AadharService } from '../aadhar.service';
import { AdminUser } from '../classes';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  admin: AdminUser = new AdminUser();
  constructor(private service: AadharService, private router: Router) { }
  message = '';
  message2 = '';
  emailToRem = '';
  waitingCitizens: any = {}
  deadCitizens: any = {}
  showDashboard = false;
  btn_area = true;
  wait_area = false;
  notAlive_area = false;
  ngOnInit(): void {
    this.btn_area = true;
    this.wait_area = false;
    this.notAlive_area = false;
    if (this.showDashboard == true)
      this.loginAsAdmin();
  }
  public home() {
    this.message = '';
    this.message2 = '';
    this.emailToRem = '';
    this.showDashboard = true;
    this.btn_area = true;
    this.wait_area = false;
    this.notAlive_area = false;
  }
  public showWaitingCitizens() {
    this.btn_area = false;
    this.wait_area = true;
    this.notAlive_area = false;
    this.service.getWaitingList().subscribe(response => this.waitingCitizens = response)
  }

  public showDeadCitizens() {
    this.btn_area = false;
    this.wait_area = false;
    this.notAlive_area = true;
    this.service.getWaitingList().subscribe(response => this.deadCitizens = response)
  }
  public loginAsAdmin() {
    this.service.checkAdminLoginCreds(this.admin.email, this.admin.password).subscribe(
      respose => {
        console.log(respose);

        if (respose == '1') {
          this.service.getAdmin(this.admin.email).subscribe(response2 => this.admin = response2)
          this.showDashboard = true;
          this.btn_area = true;
          this.wait_area = false;
          this.notAlive_area = false;

        }
        else {
          this.message = "Invalid Credentials!";
        }
      }
    )
  }
  public approve(email: string) {
    console.log(email);

    this.service.approve(email).subscribe();
    this.ngOnInit();
  }

  public reject(email: string) {
    this.service.reject(email).subscribe();
    this.ngOnInit();
  }
  public logout() {
    this.service.adminLogout(this.admin.email).subscribe();
    this.router.navigate(['']);
  }
  public update() {
    this.service.updateDeadCitizen(this.emailToRem).subscribe(
      respose => {
        console.log(respose);

        if (respose == '1') {
          this.showDashboard = true;
          this.message2 = 'Citizen Marked as not Alive'
          this.btn_area = true;
          this.wait_area = false;
          this.notAlive_area = false;
        }
        else {
          this.message2 = "Invalid email!";
        }
      }
    )
  }
}
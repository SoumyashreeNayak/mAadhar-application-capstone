import { Component } from '@angular/core';
import { AadharService } from '../aadhar.service';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
function passwordValidator(control: AbstractControl): { [key: string]: boolean } | null {
  const value = control.value;
  const hasUpper = /[A-Z]/.test(value);
  const hasLower = /[a-z]/.test(value);
  const hasNumber = /\d/.test(value);
  const hasSpecial = /[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]+/.test(value);

  if (hasUpper && hasLower && hasNumber && hasSpecial) {
    return null;
  } else {
    return { 'invalidPassword': true };
  }
}
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  adminForm: FormGroup;
  citizenForm: FormGroup;
  admin = {
    adminName: '',
    email: '',
    password: ''
  };
  citizen = {
    name: '',
    emailId: '',
    mobileNo: '',
    address: '',
    gender: 'M',
    dob: ''
  };

  constructor(private service:AadharService, private fb: FormBuilder){
    this.adminForm = this.fb.group({
      adminName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6), passwordValidator]]
    });

    this.citizenForm = this.fb.group({
      name: ['', Validators.required],
      emailId: ['', [Validators.required, Validators.email]],
      mobileNo: ['', [Validators.required, Validators.pattern('[0-9]{10}')]],
      address: ['', Validators.required],
      gender: ['M', Validators.required],
      dob: ['', Validators.required]
    });
  }
  showRegHome=true;
  showAdminForm=false;
  showCitizenForm=false;
  
  message='';
  showsAdminForm(){
    this.showRegHome=false;
    this.showAdminForm=true;
    this.showCitizenForm=false;

  }

  showsCitizenForm(){
    this.showRegHome=false;
    this.showAdminForm=false;
    this.showCitizenForm=true;
  }
  registerAdmin(){
    console.log('Admin Form Data:', this.adminForm.value);    
    this.service.registerAdmin(this.adminForm.value).subscribe(
      response=>{
        if(response=='1'){
          this.message="Registered Successfully!"
          this.showRegHome=true;
          this.showAdminForm=false;
          this.showCitizenForm=false;
        }
        else{
          this.message='Admin already exists with this email ID!'
        }

    });
  }
  registerCitizen(){
    console.log('Citizen Form Data:', this.citizenForm.value);
    this.service.registerCitizen(this.citizenForm.value).subscribe(
      response=>{
        if(response=='1'){
          this.message="Registered Successfully! Login to see your Aadhar status"
          this.showRegHome=true;
          this.showAdminForm=false;
          this.showCitizenForm=false;
        }
        else if(response=='e1'){
          this.message='Citizen already exists with this email ID!'
        }
        else if(response=='e2'){
          this.message='Citizen already exists with this Mobile Number!'
        }
        else{
          this.message='Oops, something went wrong! Try again.'
        }
    });
  }
}

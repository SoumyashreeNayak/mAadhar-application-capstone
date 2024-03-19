import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { AadharService } from './aadhar.service';
import { AdminComponent } from './admin/admin.component';
import { CitizenComponent } from './citizen/citizen.component';

const routes: Routes=[
  {
    "path":"",
    "component":HomeComponent
  },
  {
    "path":"home",
    "component":HomeComponent
  },
  {
    "path":"login",
    "component":LoginComponent
  },
  {
    "path":"register",
    "component":RegisterComponent
  },
  {
    "path":"citizen",
    "component":CitizenComponent
  },
  {
    "path":"admin",
    "component":AdminComponent
  }
]
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    AdminComponent,
    CitizenComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [AadharService],
  bootstrap: [AppComponent]
})
export class AppModule { }

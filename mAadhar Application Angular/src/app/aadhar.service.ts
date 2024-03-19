import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AdminUser } from './classes';

@Injectable({
  providedIn: 'root',
})
export class AadharService {
  constructor(private http: HttpClient) {}
//==============================ADMIN: LOGIN REGISTER LOGOUT=============================================//
  public checkAdminLoginCreds(
    email: string,
    password: string
  ): Observable<string> {
    return this.http.put<string>(
      `http://localhost:8084/admin/login/${email}/${password}`,
      null
    );
  }

  public getAdmin(email: string): Observable<any> {
    return this.http.get(`http://localhost:8084/admin/${email}`);
  }

  public adminLogout(email: string): Observable<string> {
    return this.http.put<string>(
      `http://localhost:8084/admin/logout/${email}`,
      null
    );
  }

  public registerAdmin(admin: any): Observable<string> {
    return this.http.post<string>(`http://localhost:8084/admin/`, admin);
  }
  //=============================CITIZEN: LOGIN REGISTER LOGOUT======================================//

  public checkCitizenLoginCreds(
    email: string,
    password: string
  ): Observable<string> {
    return this.http.put<string>(
      `http://localhost:8084/citizen/login/${email}/${password}`,
      null
    );
  }

  public getCitizen(email: string): Observable<any> {
    return this.http.get(`http://localhost:8084/citizen/email/${email}`);
  }
  public getAadharStatus(email: string, password: string): Observable<string> {
    return this.http.get<string>(
      `http://localhost:8084/citizen/aadharStatus/${email}/${password}`,
      { responseType: 'text' as 'json' }
    );
  }
  public citizenLogout(email: string): Observable<string> {
    return this.http.put<string>(
      `http://localhost:8084/citizen/logout/${email}`,
      null
    );
  }
  public registerCitizen(citizen: any): Observable<string> {
    return this.http.post<string>(`http://localhost:8084/citizen/`, citizen);
  }
  
  //================================ADMIN OPERATIONS ON CITIZENS====================================//

  public getWaitingList(): Observable<any> {
    return this.http.get('http://localhost:8084/admin/waiting');
  }
  public approve(email: string): Observable<any> {
    return this.http.put(`http://localhost:8084/admin/approve/${email}`, null);
  }
  public reject(email: string): Observable<any> {
    return this.http.put(`http://localhost:8084/admin/reject/${email}`, null);
  }
  public updateDeadCitizen(email:string):Observable<String>{
    return this.http.put<string>(`http://localhost:8084/admin/notAlive/${email}`, null);
  }
}

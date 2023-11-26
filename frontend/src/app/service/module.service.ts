import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({ providedIn: 'root' })
export class ModuleService {
  url =  'http://localhost:8080/v1/api/client/';

  headers = {
    'Expires': '0',
    'Pragma': 'no-cache',
    'Cache-Control': 'no-cache, no-store, must-revalidate',
    'Access-Control-Allow-Headers': 'Access-Control-Allow-Origin, Content-Type, Accept, Accept-Language, Origin, User-Agent'
  };

  constructor(private http: HttpClient) {}


  public saveModule(user: any): Observable<any> {
    return this.http.post<any>(this.url + 'new', user);
  }

  public deleteModule(id: any): Observable<any> {
    return this.http.delete<any>(this.url + 'delete/'+ id);
  }
}

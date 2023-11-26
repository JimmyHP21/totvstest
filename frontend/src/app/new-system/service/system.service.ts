import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError, throwError } from "rxjs";

@Injectable({ providedIn: 'root' })
export class SystemService {
  url =  'http://localhost:8080/v1/api/client/';

  headers = {
    'Expires': '0',
    'Pragma': 'no-cache',
    'Cache-Control': 'no-cache, no-store, must-revalidate',
    'Access-Control-Allow-Headers': 'Access-Control-Allow-Origin, Content-Type, Accept, Accept-Language, Origin, User-Agent'
  };

  constructor(private http: HttpClient) {}


  public listSystem(): Observable<any> {
    return this.http.get<any>(this.url + 'list').pipe(
      catchError(this.handleError)
    );
  }

  public getSystem(id: any): Observable<any> {
    return this.http.get<any>(this.url + id )
    .pipe(
      catchError(this.handleError)
    );
  }

  public saveSystem(user: any): Observable<any> {
    return this.http.post<any>(this.url + 'new', user)
    .pipe(
      catchError(this.handleError)
    );
  }

  public updateSystem(user: any): Observable<any> {
    return this.http.put<any>(this.url + 'update', user)
    .pipe(
      catchError(this.handleError)
    );
  }

  public deleteSystem(id: any): Observable<any> {
    return this.http.delete<any>(this.url + 'delete/'+ id)
    .pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    // Return an observable with a user-facing error message.
    return throwError(error);
  }
}

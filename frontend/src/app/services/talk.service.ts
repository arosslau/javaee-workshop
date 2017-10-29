import { BASE_URL } from './../app.tokens';
import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import 'rxjs/Rx';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class TalkService {
  constructor(private http: HttpClient, @Inject(BASE_URL) private url: string) { }

  public readTalks(): Observable<any[]> {
    return this.http.get<any[]>(`${this.url}talk`);
  }

  public update(talk: any): Observable<any> {

    return this.http.put(`${this.url}talk/${talk.id}`, talk, { responseType: 'text' });
  }

  public save(talk: any): Observable<any> {
    return this.http.post(`${this.url}talk`, talk, { responseType: 'text' });
  }

  public load(id: number): Observable<any> {
    return this.http.get<any[]>(`${this.url}talk/${id}`);
  }

  public delete(id: any): Observable<any> {
    return this.http.delete(`${this.url}talk/${id}`);
  }
}

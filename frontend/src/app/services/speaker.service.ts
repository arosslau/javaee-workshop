import { BASE_URL } from './../app.tokens';
import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import 'rxjs/Rx';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class SpeakerService {

  constructor(private http: HttpClient, @Inject(BASE_URL) private url: string) { }

  public readSpeakers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.url}person`);
  }

  public readSpeakerList(): Observable<any[]> {
    return this.readSpeakers().map(responseArray => {
      responseArray = responseArray
        .map(e => {
          return { value: e.id, label: e.firstName + ' ' + e.lastName };
        });
      responseArray.unshift({ value: '-1', label: '' });
      return responseArray;
    }
    );
  }


  public update(speaker: any): Observable<any> {

    return this.http.put(`${this.url}person/${speaker.id}`, speaker, { responseType: 'text' });
  }

  public save(speaker: any): Observable<any> {
    return this.http.post(`${this.url}person`, speaker, { responseType: 'text' });
  }

  public load(id: number): Observable<any> {
    return this.http.get<any[]>(`${this.url}person/${id}`);
  }

  public delete(id: any): Observable<any> {
    return this.http.delete(`${this.url}person/${id}`);
  }
}

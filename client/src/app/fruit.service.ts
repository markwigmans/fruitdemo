import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Fruit } from './fruit';
import { Observable } from 'rxjs';

@Injectable()
export class FruitService {

  private fruitsUrl: string;

  constructor(private http: HttpClient) {
    // TODO make serverURL configurable
    this.fruitsUrl = "http://localhost:8080" + '/fruit';
  }

  public findAll(): Observable<Fruit[]> {
    return this.http.get<Fruit[]>(this.fruitsUrl + '/all');
  }
}

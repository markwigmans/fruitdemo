import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Fruit } from './fruit';
import { Observable } from 'rxjs';
import { EnvService } from './env.service';

@Injectable({
  providedIn: 'root'
})
export class FruitService {

  private readonly fruitsUrl: string;

  constructor(private http: HttpClient, private env: EnvService) {
    this.fruitsUrl =  env.apiUrl + '/fruit';
  }

  public findAll(): Observable<Fruit[]> {
    return this.http.get<Fruit[]>(this.fruitsUrl + '/all');
  }
}

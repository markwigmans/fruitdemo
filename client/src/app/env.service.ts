import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnvService {

  // API url
  public apiUrl = 'http://localhost:8080';

  constructor() { }
}
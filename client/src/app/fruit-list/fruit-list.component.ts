import { Component, OnInit } from '@angular/core';
import { Fruit } from '../fruit';
import { FruitService } from '../fruit.service';

@Component({
  selector: 'app-fruit-list',
  templateUrl: './fruit-list.component.html',
  styleUrls: ['./fruit-list.component.css']
})
export class FruitListComponent implements OnInit {

  fruits: Fruit[];

  constructor(private fruitService: FruitService) {
  }

  ngOnInit() {
    this.fruitService.findAll().subscribe(data => {
      this.fruits = data;
    });
  }

}

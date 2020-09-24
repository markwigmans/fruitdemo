import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FruitListComponent } from './fruit-list/fruit-list.component';

const routes: Routes = [
  { path: 'fruits', component: FruitListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

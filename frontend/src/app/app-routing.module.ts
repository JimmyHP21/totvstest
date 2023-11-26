import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewSystemComponent } from './new-system/new-system.component';
import { ListSystemComponent } from './list-system/list-system.component';

const routes: Routes = [
  { path: '', component: ListSystemComponent },
  { path: 'new', component: NewSystemComponent },
  { path: 'edit/:id', component: NewSystemComponent },
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

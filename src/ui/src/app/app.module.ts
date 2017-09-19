import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { SigninComponent } from './component/signin/signin.component';
import { BungalowComponent } from './component/bungalow/bungalow.component';
import { BungalowService } from './service/bungalow.service';
import { UserService } from './service/user.service';
import { BungalowSearchComponent } from './component/bungalow-search/bungalow-search.component';
import { BungalowListComponent } from './component/bungalow-list/bungalow-list.component';
import {RouterModule, Routes} from '@angular/router';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

const appRoutes: Routes = [
  {path : 'list', component: BungalowListComponent},
  {path : 'list/:bungalows', component: BungalowListComponent},
  {path : 'search', component: BungalowSearchComponent},
  {path : 'signin', component: SigninComponent}
]
@NgModule({
  declarations: [
    AppComponent,
    BungalowComponent,
    BungalowSearchComponent,
    BungalowListComponent,
    SigninComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [BungalowService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }

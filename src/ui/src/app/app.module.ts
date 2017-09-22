import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { SigninComponent } from './component/signin/signin.component';
import { BungalowComponent } from './component/bungalow/bungalow.component';
import { BungalowService } from './service/bungalow.service';
import { UserService } from './service/user.service';
import { CartService } from './service/cart.service';
import { BungalowSearchComponent } from './component/bungalow-search/bungalow-search.component';
import { BungalowListComponent } from './component/bungalow-list/bungalow-list.component';
import {RouterModule, Routes} from '@angular/router';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { CartItemComponent } from './component/cart-item/cart-item.component';
import { CartComponent } from './component/cart/cart.component';
import { ListComponent } from './component/list/list.component';

const appRoutes: Routes = [
  {path : 'list', component: ListComponent},
  {path : 'bungalowlist/:bungalows', component: BungalowListComponent},
  {path : 'search', component: BungalowSearchComponent},
  {path : 'signin', component: SigninComponent},
  {path : 'cart', component: CartComponent}

]

@NgModule({
  declarations: [
    AppComponent,
    BungalowComponent,
    BungalowSearchComponent,
    BungalowListComponent,
    SigninComponent,
    CartItemComponent,
    CartComponent,
    ListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [BungalowService, UserService, CartService],
  bootstrap: [AppComponent]
})
export class AppModule { }

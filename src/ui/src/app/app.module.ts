import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BungalowComponent } from './model/bungalow/bungalow.component';
import {BungalowService} from './service/bungalow.component.ts';
@NgModule({
  declarations: [
    AppComponent,
    BungalowComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [BungalowService],
  bootstrap: [AppComponent]
})
export class AppModule { }

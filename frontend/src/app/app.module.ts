import { BASE_URL } from './app.tokens';
import { ErrorHandlerService } from './services/error-handler.service';
import { SpeakerService } from './services/speaker.service';
import { TalkService } from './services/talk.service';
import { FormsModule } from '@angular/forms';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { NgModule, InjectionToken, ErrorHandler } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TalkComponent } from './components/talk/talk.component';
import { HomeComponent } from './components/home/home.component';
import { TalkEditComponent } from './components/talk/talk-edit/talk-edit.component';
import { SpeakerComponent } from './components/speaker/speaker.component';
import { SpeakerEditComponent } from './components/speaker/speaker-edit/speaker-edit.component';
import { DateDirective } from './directives/date.directive';

@NgModule({
  declarations: [
    AppComponent,
    TalkComponent,
    HomeComponent,
    TalkEditComponent,
    SpeakerComponent,
    SpeakerEditComponent,
    DateDirective
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    TalkService,
    SpeakerService,
    DateDirective,
    {
      provide: BASE_URL,
      useValue: '/javaee-workshop-backend/rest/'
    },
    {
      provide: ErrorHandler,
      useClass: ErrorHandlerService
    },
    {
      provide: LocationStrategy, useClass: HashLocationStrategy
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

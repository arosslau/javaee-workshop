import { SpeakerEditComponent } from './components/speaker/speaker-edit/speaker-edit.component';
import { SpeakerComponent } from './components/speaker/speaker.component';
import { TalkEditComponent } from './components/talk/talk-edit/talk-edit.component';
import { TalkComponent } from './components/talk/talk.component';
import { HomeComponent } from './components/home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'talk', component: TalkComponent },
  { path: 'talk/edit', component: TalkEditComponent},
  { path: 'talk/edit/:id', component: TalkEditComponent},
  { path: 'speaker', component: SpeakerComponent },
  { path: 'speaker/edit', component: SpeakerEditComponent },
  { path: 'speaker/edit/:id', component: SpeakerEditComponent },
  { path: 'talk-extended', component: TalkComponent },
  { path: 'talk-extended/edit', component: TalkEditComponent, data: { 'extended': true } },
  { path: 'talk-extended/edit/:id', component: TalkEditComponent, data: { 'extended': true } }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

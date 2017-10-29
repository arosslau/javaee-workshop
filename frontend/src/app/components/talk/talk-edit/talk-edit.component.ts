import { SpeakerService } from './../../../services/speaker.service';
import { Observable } from 'rxjs/Observable';
import { TalkService } from './../../../services/talk.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'ged-talk-edit',
  templateUrl: './talk-edit.component.html',
  styleUrls: ['./talk-edit.component.css']
})
export class TalkEditComponent implements OnInit {

  public talk: any = {};

  public extendedFormular = false;

  public speaker: Observable<any>;

  public selectedSpeaker: any[] = [];

  private editId: number;


  constructor(private talkService: TalkService, private speakerService: SpeakerService, private router: Router, route: ActivatedRoute) {
    this.extendedFormular = route.snapshot.data['extended'];
    route.params.subscribe(p => {
      this.editId = p.id;
      if (p.id) {
        this.talkService.load(p.id).subscribe(r => {
          this.talk = r;
          this.selectedSpeaker.push(...r.speakers.map(s => { return { id: s.id } }));
        });
      } else {
        this.talk = {};
        if (this.extendedFormular) { this.addSpeaker(); }
      }

      if (this.extendedFormular) {
        this.speaker = this.speakerService.readSpeakerList();
      }
    });

  }


  ngOnInit() {
  }

  submit() {
    const sp = this.selectedSpeaker.filter(s => s.id !== '-1');
    if (sp.length > 0) {
      this.talk.speakers = sp;
    }

    if (!this.editId) {
      this.talkService.save(this.talk).subscribe(r => {
        this.router.navigateByUrl(this.extendedFormular ? 'talk-extended' : 'talk');
      });
    } else {
      this.talkService.update(this.talk).subscribe(r => {
        this.router.navigateByUrl(this.extendedFormular ? 'talk-extended' : 'talk');
      });
    }
  }

  addSpeaker() {
    this.selectedSpeaker.push({ id: -1 });
  }

}

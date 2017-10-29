import { Router } from '@angular/router';
import { SpeakerService } from './../../services/speaker.service';

import { Observable } from 'rxjs/Observable';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ged-speaker',
  templateUrl: './speaker.component.html',
  styleUrls: ['./speaker.component.css']
})
export class SpeakerComponent implements OnInit {

  public speaker: Observable<any[]>;

  constructor(public speakerService: SpeakerService, private router: Router) { }

  ngOnInit() {
    this.loadData();

  }

  private loadData() {
    this.speaker = this.speakerService.readSpeakers();
  }

  delete(id: number) {
    this.speakerService.delete(id).subscribe(r => {
      this.loadData();
    });
  }

}

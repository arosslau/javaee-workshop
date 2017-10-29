import { Observable } from 'rxjs/Observable';
import { TalkService } from './../../services/talk.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'ged-talk',
  templateUrl: './talk.component.html',
  styleUrls: ['./talk.component.css']
})
export class TalkComponent implements OnInit {

  public talks: Observable<any[]>;

  constructor(public talkService: TalkService, private router: Router) { }

  ngOnInit() {
    this.loadData();

  }

  private loadData() {
    this.talks = this.talkService.readTalks();
  }

  delete(id: number) {
    this.talkService.delete(id).subscribe(r => {
      this.loadData();
    });
  }

}

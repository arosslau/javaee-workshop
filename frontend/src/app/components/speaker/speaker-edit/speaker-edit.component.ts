import { Router, ActivatedRoute } from '@angular/router';
import { SpeakerService } from './../../../services/speaker.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ged-speaker-edit',
  templateUrl: './speaker-edit.component.html',
  styleUrls: ['./speaker-edit.component.css']
})
export class SpeakerEditComponent implements OnInit {

  public speaker: any = {};

  private editId: number;

  constructor(private speakerService: SpeakerService, private router: Router, route: ActivatedRoute) {
    route.params.subscribe(p => {
      this.editId = p.id;
      if (p.id) {
        this.speakerService.load(p.id).subscribe(r => {
          this.speaker = r;
        }
      );
      } else {
        this.speaker = {};
      }
    });
  }

  ngOnInit() {
  }

  submit() {
    if (!this.editId) {
      this.speakerService.save(this.speaker).subscribe(r => {
        this.router.navigateByUrl('speaker');
      });
    } else {
      this.speakerService.update(this.speaker).subscribe(r => {
        this.router.navigateByUrl('speaker');
      });
    }
  }
}

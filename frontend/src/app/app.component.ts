import { Observable } from 'rxjs/Observable';
import { Component, ApplicationRef, ErrorHandler } from '@angular/core';

@Component({
  selector: 'ged-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  error: Error;

  constructor(errorService: ErrorHandler, public app: ApplicationRef) {
    (<any>errorService).errors.subscribe(e => {
      this.error = e;
      this.app.tick();
      setTimeout(() => {
        this.error = null;
        this.app.tick();
      }, 5000);
    });
  }

}

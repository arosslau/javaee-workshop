import { Injectable, ErrorHandler } from '@angular/core';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ErrorHandlerService implements ErrorHandler {

  errors: Subject<Error>;

  constructor() {
    this.errors = new Subject<Error>();
  }

  handleError(e: Error) {
    console.log(e);
    this.errors.next(e);
  }
}

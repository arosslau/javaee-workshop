import { Directive, ElementRef, HostListener, Renderer, forwardRef } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ControlValueAccessor, NG_VALUE_ACCESSOR, Validator } from '@angular/forms';

import * as moment from 'moment';

@Directive({
  selector: '[gedDate]',
  providers: [
    { provide: NG_VALUE_ACCESSOR, useExisting: forwardRef(() => DateDirective), multi: true }
  ]
})
export class DateDirective implements ControlValueAccessor {

  constructor(private _renderer: Renderer, private _elementRef: ElementRef) { }

  _onChange: any;
  propagateChange = (_: any) => { };

  writeValue(obj: number): void {
    let value = '';
    if (obj) {
      value = moment(obj).format('DD.MM.YYYY HH:mm');
    }
    this._renderer.setElementProperty(this._elementRef.nativeElement, 'value', value);
  }

  registerOnTouched(fn: any): void { }

  @HostListener('change', ['$event.target'])
  onInput(target: any) {
    if (moment(target.value, 'DD.MM.YYYY HH:mm', true).isValid()) {
      const ut = moment(target.value, 'DD.MM.YYYY HH:mm').format('x');
      this._onChange(ut);
    } else {
      throw Error('Invalid Date');
    }
  }

  registerOnChange(fn: (_: any) => void): void {
    this._onChange = fn;
  }
}

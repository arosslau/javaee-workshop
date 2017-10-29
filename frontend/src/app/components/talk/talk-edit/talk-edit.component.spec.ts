import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TalkEditComponent } from './talk-edit.component';

describe('TalksEditComponent', () => {
  let component: TalkEditComponent;
  let fixture: ComponentFixture<TalkEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TalkEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TalkEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

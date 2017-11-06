package de.gedoplan.workshop.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Talk {
  private Integer id;
  private String title;
  private TalkType type;
  private Date start;
  private int minutes;

  public Talk(String title, TalkType type, Date start, int minutes) {
    this.title = title;
    this.type = type;
    this.start = start;
    this.minutes = minutes;
  }

  protected Talk() {
  }
}

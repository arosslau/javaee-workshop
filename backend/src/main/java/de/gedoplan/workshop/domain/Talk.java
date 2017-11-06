package de.gedoplan.workshop.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Talk {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @XmlAttribute
  private Integer id;

  private String title;

  @Column(name = "TALK_TYPE")
  @Enumerated(EnumType.STRING)
  private TalkType type;

  @Temporal(TemporalType.TIMESTAMP)
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

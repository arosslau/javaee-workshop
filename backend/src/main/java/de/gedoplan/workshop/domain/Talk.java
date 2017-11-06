package de.gedoplan.workshop.domain;

import de.gedoplan.baselibs.persistence.entity.GeneratedIntegerIdEntity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Talk extends GeneratedIntegerIdEntity {

  private String title;

  @Column(name = "TALK_TYPE")
  @Enumerated(EnumType.STRING)
  private TalkType type;

  @Temporal(TemporalType.TIMESTAMP)
  private Date start;

  private int minutes;

  @ManyToMany(fetch = FetchType.EAGER)
  Set<Person> speakers;

  public Talk(String title, TalkType type, Date start, int minutes) {
    this.title = title;
    this.type = type;
    this.start = start;
    this.minutes = minutes;

    this.speakers = new HashSet<>();
  }

  protected Talk() {
  }

}

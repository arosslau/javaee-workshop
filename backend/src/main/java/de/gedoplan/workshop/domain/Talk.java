package de.gedoplan.workshop.domain;

import de.gedoplan.baselibs.persistence.entity.GeneratedIntegerIdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Talk extends GeneratedIntegerIdEntity
{
    @Column(name = "title")
    private String title;

    @Column(name = "talk_type")
    @Enumerated(EnumType.STRING)
    private TalkType type;

    @Column(name = "start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @Column(name = "minutes")
    private int minutes;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Person> speakers;

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

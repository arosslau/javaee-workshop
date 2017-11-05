package de.gedoplan.workshop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
  private Integer id;

  private String lastName;

  private String firstName;

  public Person(String lastName, String firstName) {
    this.lastName = lastName;
    this.firstName = firstName;
  }
}

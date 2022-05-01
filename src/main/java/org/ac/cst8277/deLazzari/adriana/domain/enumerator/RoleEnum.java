package org.ac.cst8277.deLazzari.adriana.domain.enumerator;

import lombok.Getter;

@Getter
public enum RoleEnum {

  PRODUCER(1),
  SUBSCRIBER(2);

  private final Integer id;

  RoleEnum(Integer id) {
    this.id = id;
  }
}

package org.ac.cst8277.deLazzari.adriana.domain.entity;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "message")
public class MessageEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String content;
  private Instant date;

  @Column(name = "user_id")
  private Integer userId;
}

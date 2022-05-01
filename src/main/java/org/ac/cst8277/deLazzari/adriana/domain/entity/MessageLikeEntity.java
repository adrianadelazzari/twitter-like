package org.ac.cst8277.deLazzari.adriana.domain.entity;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import lombok.Data;

@Data
@Entity(name = "message_like")
@IdClass(MessageLikeId.class)
public class MessageLikeEntity {

  @Id
  @Column(name = "message_id")
  private Integer messageId;

  @Id
  @Column(name = "user_id")
  private Integer userId;

  private Instant date;
}

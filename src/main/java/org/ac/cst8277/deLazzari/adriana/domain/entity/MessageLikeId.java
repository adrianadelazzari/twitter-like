package org.ac.cst8277.deLazzari.adriana.domain.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class MessageLikeId implements Serializable {

  private Integer messageId;

  private Integer userId;
}

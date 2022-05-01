package org.ac.cst8277.deLazzari.adriana.domain.valueObject;

import java.time.Instant;
import lombok.Data;

@Data
public class MessageVO {

  private Integer id;
  private String content;
  private Instant date;
  private Integer userId;
}

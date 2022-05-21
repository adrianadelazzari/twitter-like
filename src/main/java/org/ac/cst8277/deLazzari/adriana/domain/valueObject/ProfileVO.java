package org.ac.cst8277.deLazzari.adriana.domain.valueObject;

import java.util.Date;
import lombok.Data;

@Data
public class ProfileVO {

  private String location;
  private Date dateOfBirth;
  private String name;
  private String email;

}

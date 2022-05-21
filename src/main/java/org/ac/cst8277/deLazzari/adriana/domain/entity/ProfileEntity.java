package org.ac.cst8277.deLazzari.adriana.domain.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "profile")
public class ProfileEntity {

  @Id
  @Column(name = "user_id")
  private Integer userId;
  private String location;
  private Date dateOfBirth;
  private String name;
  private String email;
}

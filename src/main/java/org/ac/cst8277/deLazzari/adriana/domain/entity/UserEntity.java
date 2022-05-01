package org.ac.cst8277.deLazzari.adriana.domain.entity;

import java.time.Instant;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity(name = "user")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String username;
  private String email;
  private String password;
  private String uuid;
  @CreationTimestamp
  private Instant created;

  @ManyToMany
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private List<RoleEntity> roleList;

  @ManyToMany
  @JoinTable(
      name = "producer_subscriber",
      joinColumns = @JoinColumn(name = "producer_id"),
      inverseJoinColumns = @JoinColumn(name = "subscriber_id")
  )
  private List<UserEntity> producerList;

  @ManyToMany
  @JoinTable(
      name = "producer_subscriber",
      joinColumns = @JoinColumn(name = "subscriber_id"),
      inverseJoinColumns = @JoinColumn(name = "producer_id")
  )
  private List<UserEntity> subscriberList;
}

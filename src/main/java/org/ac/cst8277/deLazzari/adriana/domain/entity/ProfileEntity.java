package org.ac.cst8277.deLazzari.adriana.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "profile")
public class ProfileEntity {
    @Id
    @Column(name="user_id")
    private Integer userId;

    private String location;
    private Date dateOfBirth;
    private String name;
}

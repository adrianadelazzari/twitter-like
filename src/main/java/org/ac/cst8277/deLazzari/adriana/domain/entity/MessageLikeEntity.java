package org.ac.cst8277.deLazzari.adriana.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity(name = "message_like")
@IdClass(MessageLikeId.class)
public class MessageLikeEntity {

    @Id
    @Column(name="message_id")
    private Integer messageId;

    @Id
    @Column(name="user_id")
    private Integer userId;

    private Instant date;
}

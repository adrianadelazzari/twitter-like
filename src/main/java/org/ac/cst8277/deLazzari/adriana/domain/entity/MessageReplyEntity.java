package org.ac.cst8277.deLazzari.adriana.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity(name = "message_reply")
public class MessageReplyEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String content;
    private Instant date;

    @Column(name="message_id")
    private Integer messageId;

    @Column(name="user_id")
    private Integer userId;
}

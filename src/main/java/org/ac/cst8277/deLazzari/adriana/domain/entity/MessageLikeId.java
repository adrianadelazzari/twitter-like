package org.ac.cst8277.deLazzari.adriana.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageLikeId implements Serializable {

    private Integer messageId;

    private Integer userId;
}

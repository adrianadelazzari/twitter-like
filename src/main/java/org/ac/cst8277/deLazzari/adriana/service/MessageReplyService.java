package org.ac.cst8277.deLazzari.adriana.service;

import lombok.RequiredArgsConstructor;
import org.ac.cst8277.deLazzari.adriana.domain.entity.MessageReplyEntity;
import org.ac.cst8277.deLazzari.adriana.domain.repository.MessageReplyRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageReplyService {

  private final MessageReplyRepository messageReplyRepository;

  public MessageReplyEntity save(MessageReplyEntity messageReplyEntity) {

    return null;
  }

  public Boolean deleteById(Integer messageReplyId) {
    return true;
  }
}

package org.ac.cst8277.deLazzari.adriana.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ac.cst8277.deLazzari.adriana.domain.entity.MessageEntity;
import org.ac.cst8277.deLazzari.adriana.domain.repository.MessageRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageService {

  private final MessageRepository messageRepository;

  public MessageEntity save(MessageEntity messageEntity) {

    return null;
  }

  public Boolean deleteById(Integer messageId) {

    return true;
  }

  public List<MessageEntity> getMessagesForUser(Integer userId) {
    return null;
  }
}

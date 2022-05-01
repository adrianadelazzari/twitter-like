package org.ac.cst8277.deLazzari.adriana.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ac.cst8277.deLazzari.adriana.domain.entity.MessageEntity;
import org.ac.cst8277.deLazzari.adriana.domain.entity.UserEntity;
import org.ac.cst8277.deLazzari.adriana.domain.repository.MessageRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageService {

  private final MessageRepository messageRepository;

  public MessageEntity save(MessageEntity messageEntity) {
    return this.messageRepository.save(messageEntity);
  }

  public MessageEntity findById(Integer messageId) {
    return this.messageRepository.findById(messageId).orElse(null);
  }

  public void deleteById(Integer messageId) {
    this.messageRepository.deleteById(messageId);
  }

  public List<MessageEntity> getMessagesForUser(UserEntity userEntity) {
    List<Integer> producerIdList = new ArrayList<>();
    userEntity.getProducerList().forEach(userEntity1 -> {
      producerIdList.add(userEntity1.getId());
    });
    return this.messageRepository.findAllByUserIdIn(producerIdList);
  }
}

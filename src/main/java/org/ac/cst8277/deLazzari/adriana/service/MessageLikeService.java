package org.ac.cst8277.deLazzari.adriana.service;

import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.ac.cst8277.deLazzari.adriana.domain.entity.MessageLikeEntity;
import org.ac.cst8277.deLazzari.adriana.domain.entity.MessageLikeId;
import org.ac.cst8277.deLazzari.adriana.domain.repository.MessageLikeRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageLikeService {

  private final MessageLikeRepository messageLikeRepository;

  public void like(Integer messageId, Integer userId) {
    MessageLikeEntity messageLikeEntity = new MessageLikeEntity();
    messageLikeEntity.setMessageId(messageId);
    messageLikeEntity.setUserId(userId);
    messageLikeEntity.setDate(Instant.now());
    this.messageLikeRepository.save(messageLikeEntity);
  }

  public void deleteById(Integer messageId, Integer userId) {
    MessageLikeId messageLikeId = new MessageLikeId();
    messageLikeId.setMessageId(messageId);
    messageLikeId.setUserId(userId);
    this.messageLikeRepository.deleteById(messageLikeId);
  }
}

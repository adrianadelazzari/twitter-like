package org.ac.cst8277.deLazzari.adriana.domain.repository;

import java.util.List;
import org.ac.cst8277.deLazzari.adriana.domain.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {

  List<MessageEntity> findAllByUserIdIn(List<Integer> userIdList);
}

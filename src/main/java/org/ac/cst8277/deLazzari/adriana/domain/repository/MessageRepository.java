package org.ac.cst8277.deLazzari.adriana.domain.repository;

import org.ac.cst8277.deLazzari.adriana.domain.entity.MessageEntity;
import org.ac.cst8277.deLazzari.adriana.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {

    List<MessageEntity> findAllByUserIdIn(List<Integer> userIdList);
}

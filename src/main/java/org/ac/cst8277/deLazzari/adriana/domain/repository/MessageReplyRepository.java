package org.ac.cst8277.deLazzari.adriana.domain.repository;

import org.ac.cst8277.deLazzari.adriana.domain.entity.MessageReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageReplyRepository extends JpaRepository<MessageReplyEntity, Integer> {

}

package org.ac.cst8277.deLazzari.adriana.domain.repository;

import org.ac.cst8277.deLazzari.adriana.domain.entity.MessageLikeEntity;
import org.ac.cst8277.deLazzari.adriana.domain.entity.MessageLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageLikeRepository extends JpaRepository<MessageLikeEntity, MessageLikeId> {

}

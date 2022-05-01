package org.ac.cst8277.deLazzari.adriana.domain.repository;

import org.ac.cst8277.deLazzari.adriana.domain.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Integer> {

  @Query(
      value = "SELECT s.* FROM SESSION s JOIN USER u ON s.user_id = u.id " +
          "WHERE u.uuid = :uuid " +
          "ORDER BY s.id DESC " +
          "LIMIT 1",
      nativeQuery = true)
  SessionEntity findFirstByUuiIdOrderByIdDesc(@Param("uuid") String uuid);
}

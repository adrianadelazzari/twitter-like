package org.ac.cst8277.deLazzari.adriana.domain.repository;

import org.ac.cst8277.deLazzari.adriana.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    Optional<UserEntity> findByUuid(String uuid);

    @Query(
            value = "SELECT u.* FROM USER u JOIN USER_ROLE ur ON u.id = ur.user_id " +
                    "WHERE ur.role_id = 1",
            nativeQuery = true)
    List<UserEntity> findAllProducers();
}

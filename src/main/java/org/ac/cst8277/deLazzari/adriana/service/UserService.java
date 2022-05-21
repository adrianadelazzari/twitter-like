package org.ac.cst8277.deLazzari.adriana.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ac.cst8277.deLazzari.adriana.domain.entity.UserEntity;
import org.ac.cst8277.deLazzari.adriana.domain.repository.UserRepository;
import org.ac.cst8277.deLazzari.adriana.exception.TwitterLikeException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  public UserEntity save(UserEntity userEntity) {
    return this.userRepository.save(userEntity);
  }

  public UserEntity findByUsername(String username)
      throws TwitterLikeException {
    return this.userRepository.findByUsername(username)
        .orElseThrow(() -> new TwitterLikeException("user not found."));
  }

  public UserEntity findById(Integer userId) throws TwitterLikeException {
    return this.userRepository.findById(userId)
        .orElseThrow(() -> new TwitterLikeException("user not found."));
  }

  public List<UserEntity> findAllProducers() {
    return this.userRepository.findAllProducers();
  }
}

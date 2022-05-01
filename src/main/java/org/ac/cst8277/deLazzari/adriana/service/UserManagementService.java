package org.ac.cst8277.deLazzari.adriana.service;

import lombok.RequiredArgsConstructor;
import org.ac.cst8277.deLazzari.adriana.domain.entity.UserEntity;
import org.ac.cst8277.deLazzari.adriana.exception.TwitterLikeException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserManagementService {

  private final UserService userService;

  public void createUser(UserEntity userEntity) {

    this.userService.save(userEntity);
  }

  public UserEntity findByEmailAndPassword(String email, String password)
      throws TwitterLikeException {

    return this.userService.findByEmailAndPassword(email, password);
  }
}

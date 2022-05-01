package org.ac.cst8277.deLazzari.adriana.web.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.ac.cst8277.deLazzari.adriana.domain.entity.RoleEntity;
import org.ac.cst8277.deLazzari.adriana.domain.entity.SessionEntity;
import org.ac.cst8277.deLazzari.adriana.domain.entity.UserEntity;
import org.ac.cst8277.deLazzari.adriana.domain.enumerator.RoleEnum;
import org.ac.cst8277.deLazzari.adriana.domain.valueObject.LoginVO;
import org.ac.cst8277.deLazzari.adriana.domain.valueObject.TokenVO;
import org.ac.cst8277.deLazzari.adriana.domain.valueObject.UserRegisterVO;
import org.ac.cst8277.deLazzari.adriana.service.SessionService;
import org.ac.cst8277.deLazzari.adriana.service.UserManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "authentication")
@Tag(name = "authentication", description = "Authentication API")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successful operation")})
@RequiredArgsConstructor
public class AuthenticationController {

  private final UserManagementService userManagementService;
  private final SessionService sessionService;

  //@Operation(summary = "Find Contacts by name", description = "Name search by %name% format")
  @PostMapping("/register")
  public void register(@RequestBody UserRegisterVO userRegisterVO) {

    try {
      UserEntity userEntity = new UserEntity();
      userEntity.setUsername(userRegisterVO.getUsername());
      userEntity.setEmail(userRegisterVO.getEmail());
      userEntity.setPassword(userRegisterVO.getPassword());
      userEntity.setUuid(UUID.randomUUID().toString());

      RoleEntity roleEntity = new RoleEntity();
      roleEntity.setId(RoleEnum.SUBSCRIBER.getId());
      List<RoleEntity> roleEntityList = new ArrayList<>();
      roleEntityList.add(roleEntity);
      userEntity.setRoleList(roleEntityList);

      this.userManagementService.createUser(userEntity);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/login")
  public TokenVO login(@RequestBody LoginVO loginVO) {
    try {
      UserEntity userEntity = this.userManagementService.findByEmailAndPassword(loginVO.getEmail(),
          loginVO.getPassword());

      SessionEntity sessionEntity = new SessionEntity();
      sessionEntity.setUserId(userEntity.getId());
      sessionEntity.setLogin(Instant.now());
      this.sessionService.save(sessionEntity);

      TokenVO tokenVO = new TokenVO();
      tokenVO.setToken(userEntity.getUuid());
      return tokenVO;
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/logout")
  public void logout(@RequestAttribute("userEntity") UserEntity userEntity) {
    try {
      SessionEntity sessionEntity = this.sessionService.findFirstByUuiIdOrderByIdDesc(
          userEntity.getUuid());
      sessionEntity.setLogout(Instant.now());
      this.sessionService.save(sessionEntity);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}

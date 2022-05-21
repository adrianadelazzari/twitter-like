package org.ac.cst8277.deLazzari.adriana.web.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ac.cst8277.deLazzari.adriana.domain.entity.RoleEntity;
import org.ac.cst8277.deLazzari.adriana.domain.entity.UserEntity;
import org.ac.cst8277.deLazzari.adriana.domain.enumerator.RoleEnum;
import org.ac.cst8277.deLazzari.adriana.domain.valueObject.TokenVO;
import org.ac.cst8277.deLazzari.adriana.exception.TwitterLikeException;
import org.ac.cst8277.deLazzari.adriana.service.UserManagementService;
import org.ac.cst8277.deLazzari.adriana.util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "authentication")
@Tag(name = "authentication", description = "Authentication API")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successful operation")})
@RequiredArgsConstructor
public class AuthenticationController {

  private final UserManagementService userManagementService;
  private final JwtTokenUtil jwtTokenUtil;

  @GetMapping("/token")
  public TokenVO getToken(
      @Parameter(hidden = true) @AuthenticationPrincipal OAuth2User principal) {
    try {
      String login = principal.getAttribute("login");
      if (login == null || "".equals(login)) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
      }

      UserEntity userEntity = null;
      try {
        userEntity = this.userManagementService.findByUsername(
            login);
      } catch (TwitterLikeException t) {
        // No need to propagate if user doesn't exist.
      }

      if (userEntity == null) {
        // Create and save
        userEntity = new UserEntity();
        userEntity.setUsername(login);

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(RoleEnum.SUBSCRIBER.getId());
        List<RoleEntity> roleEntityList = new ArrayList<>();
        roleEntityList.add(roleEntity);
        userEntity.setRoleList(roleEntityList);

        this.userManagementService.createUser(userEntity);
      }

      // get token
      TokenVO tokenVO = new TokenVO();
      tokenVO.setToken(this.jwtTokenUtil.generateToken(userEntity.getUsername()));
      return tokenVO;
    } catch (ResponseStatusException e) {
      throw e;
    } catch (Exception e) {
      e.printStackTrace();
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }
}

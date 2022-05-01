package org.ac.cst8277.deLazzari.adriana.web.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ac.cst8277.deLazzari.adriana.domain.entity.UserEntity;
import org.ac.cst8277.deLazzari.adriana.domain.valueObject.ProfileVO;
import org.ac.cst8277.deLazzari.adriana.domain.valueObject.UserVO;
import org.ac.cst8277.deLazzari.adriana.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "user")
@Tag(name = "user", description = "User API")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successful operation"),
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(examples = {}))})
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/producers")
  public List<UserVO> getProducers(@RequestAttribute("userEntity") UserEntity userEntity) {
    UserVO temp = new UserVO();
    temp.setId(1);
    temp.setUsername("username");
    return Collections.singletonList(temp);
  }

  @PostMapping("/subscribe")
  public void subscribe(@RequestAttribute("userEntity") UserEntity userEntity,
      @RequestBody int userId) {

  }

  @PostMapping("/unsubscribe")
  public void unsubscribe(@RequestAttribute("userEntity") UserEntity userEntity,
      @RequestBody int userId) {

  }

  @PutMapping("/profile")
  public void updateProfile(@RequestAttribute("userEntity") UserEntity userEntity,
      @RequestBody ProfileVO profileVO) {

  }

  @GetMapping("/producers/all")
  public List<UserVO> getAllProducers() {
    List<UserEntity> userEntityList = this.userService.findAllProducers();
    List<UserVO> userVOList = new ArrayList<>();
    userEntityList.forEach(userEntity -> {
      UserVO userVO = new UserVO();
      userVO.setUsername(userEntity.getUsername());
      userVOList.add(userVO);
    });
    return userVOList;
  }

  @GetMapping("/subscribers")
  public List<UserVO> getSubscribers(@RequestAttribute("userEntity") UserEntity userEntity) {
    UserVO temp = new UserVO();
    return Collections.singletonList(temp);
  }

  @DeleteMapping("/subscribers/{userId}")
  public void deleteSubscriber(@RequestAttribute("userEntity") UserEntity userEntity,
      @PathVariable("userId") Integer userId) {

  }

  @GetMapping("/profile")
  public ProfileVO getProfile(@RequestAttribute("userEntity") UserEntity userEntity) {

    ProfileVO temp = new ProfileVO();
    return temp;
  }
}

package org.ac.cst8277.deLazzari.adriana.web.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ac.cst8277.deLazzari.adriana.domain.entity.ProfileEntity;
import org.ac.cst8277.deLazzari.adriana.domain.entity.UserEntity;
import org.ac.cst8277.deLazzari.adriana.domain.enumerator.RoleEnum;
import org.ac.cst8277.deLazzari.adriana.domain.valueObject.ProfileVO;
import org.ac.cst8277.deLazzari.adriana.domain.valueObject.UserVO;
import org.ac.cst8277.deLazzari.adriana.exception.TwitterLikeException;
import org.ac.cst8277.deLazzari.adriana.service.ProfileService;
import org.ac.cst8277.deLazzari.adriana.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "user")
@Tag(name = "user", description = "User API")
@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation"),
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(examples = {}))})
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final ProfileService profileService;

  @GetMapping("/producers")
  public List<UserVO> getProducers(@RequestAttribute("userEntity") UserEntity userEntity) {
    List<UserEntity> userEntityList = userEntity.getProducerList();
    List<UserVO> userVOList = new ArrayList<>();
    userEntityList.forEach(userEntityTemp -> {
      UserVO userVO = new UserVO();
      userVO.setUsername(userEntityTemp.getUsername());
      userVOList.add(userVO);
    });
    return userVOList;
  }

  @PostMapping("/subscribe")
  public void subscribe(@RequestAttribute("userEntity") UserEntity userEntity,
      @RequestBody int userId) {
    if (userEntity.getId().equals(userId)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot subscribe to itself.");
    }
    try {
      UserEntity userEntity1 = this.userService.findById(userId);
      boolean isProducer = userEntity1.getRoleList().stream()
          .anyMatch(roleEntity -> RoleEnum.PRODUCER.getId().equals(roleEntity.getId()));
      if (isProducer) {
        List<UserEntity> userEntityList = userEntity.getProducerList();
        userEntityList.add(userEntity1);
        this.userService.save(userEntity);
      } else {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Cannot subscribe to non producer.");
      }
    } catch (TwitterLikeException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/unsubscribe")
  public void unsubscribe(@RequestAttribute("userEntity") UserEntity userEntity,
      @RequestBody int userId) {
    List<UserEntity> userEntityList = userEntity.getProducerList();
    userEntityList.removeIf(userEntity1 -> userEntity1.getId().equals(userId));
    this.userService.save(userEntity);
  }

  @PutMapping("/profile")
  public void updateProfile(@RequestAttribute("userEntity") UserEntity userEntity,
      @RequestBody ProfileVO profileVO) {
    ProfileEntity profileEntity = this.profileService.findByUserId(userEntity.getId());
    if (profileEntity == null) {
      profileEntity = new ProfileEntity();
      profileEntity.setUserId(userEntity.getId());
    }
    profileEntity.setName(profileVO.getName());
    profileEntity.setLocation(profileVO.getLocation());
    profileEntity.setDateOfBirth(profileVO.getDateOfBirth());
    profileEntity.setEmail(profileVO.getEmail());
    this.profileService.save(profileEntity);
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
    List<UserEntity> userEntityList = userEntity.getSubscriberList();
    List<UserVO> userVOList = new ArrayList<>();
    userEntityList.forEach(userEntityTemp -> {
      UserVO userVO = new UserVO();
      userVO.setUsername(userEntityTemp.getUsername());
      userVOList.add(userVO);
    });
    return userVOList;
  }

  @DeleteMapping("/subscribers/{userId}")
  public void deleteSubscriber(@RequestAttribute("userEntity") UserEntity userEntity,
      @PathVariable("userId") Integer userId) {
    List<UserEntity> userEntityList = userEntity.getSubscriberList();
    userEntityList.removeIf(userEntity1 -> userEntity1.getId().equals(userId));
    this.userService.save(userEntity);
  }

  @GetMapping("/profile")
  public ProfileVO getProfile(@RequestAttribute("userEntity") UserEntity userEntity) {
    ProfileEntity profileEntity = this.profileService.findByUserId(userEntity.getId());
    ProfileVO profileVO = new ProfileVO();
    if (profileEntity != null) {
      profileVO.setName(profileEntity.getName());
      profileVO.setLocation(profileEntity.getLocation());
      profileVO.setDateOfBirth(profileEntity.getDateOfBirth());
      profileVO.setEmail(profileEntity.getEmail());
    }
    return profileVO;
  }
}

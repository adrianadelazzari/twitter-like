package org.ac.cst8277.deLazzari.adriana.web.controller;

import lombok.RequiredArgsConstructor;
import org.ac.cst8277.deLazzari.adriana.domain.entity.UserEntity;
import org.ac.cst8277.deLazzari.adriana.domain.valueObject.ProfileVO;
import org.ac.cst8277.deLazzari.adriana.domain.valueObject.UserVO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.ac.cst8277.deLazzari.adriana.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "user")
@Tag(name = "user", description = "User API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(examples = {})) })
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/producers")
    public List<UserVO> getProducers() {
        UserVO temp = new UserVO();
        temp.setId(1);
        temp.setUsername("username");
        return Collections.singletonList(temp);
    }

    @PostMapping("/subscribe")
    public void subscribe(@RequestBody int userId){

    }

    @PostMapping("/unsubscribe")
    public void unsubscribe(@RequestBody int userId){

    }

    @PutMapping("/profile")
    public void updateProfile(@RequestBody ProfileVO profileVO){

    }

    @GetMapping("/producers/all")
    public List<UserVO> getAllProducers(){

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
    public List<UserVO> getSubscribers(){
        UserVO temp = new UserVO();
        return Collections.singletonList(temp);
    }

    @DeleteMapping("/subscribers/{userId}")
    public void deleteSubscriber(@PathVariable("userId") Integer userId){

    }

    @GetMapping("/profile")
    public ProfileVO getProfile(){

        ProfileVO temp = new ProfileVO();
        return temp;
    }
}

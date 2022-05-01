package org.ac.cst8277.deLazzari.adriana.web.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Collections;
import java.util.List;
import org.ac.cst8277.deLazzari.adriana.domain.entity.UserEntity;
import org.ac.cst8277.deLazzari.adriana.domain.valueObject.MessageVO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "message")
@Tag(name = "message", description = "Message API")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successful operation"),
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(examples = {}))})
public class MessageController {

  @PostMapping("/")
  public void publish(@RequestAttribute("userEntity") UserEntity userEntity,
      @RequestBody MessageVO messageVO) {

  }

  @PostMapping("/{messageId}/reply")
  public void reply(@RequestAttribute("userEntity") UserEntity userEntity,
      @RequestBody MessageVO messageVO, @PathVariable("messageId") Integer messageId) {
    System.out.println(123);
  }

  @PostMapping("/like")
  public void like(@RequestAttribute("userEntity") UserEntity userEntity,
      @RequestBody Integer messageId) {

  }

  @GetMapping("/all")
  public List<MessageVO> getMessages(@RequestAttribute("userEntity") UserEntity userEntity) {
    MessageVO temp = new MessageVO();
    return Collections.singletonList(temp);
  }

  @DeleteMapping("/{messageId}")
  public void deleteMessage(@RequestAttribute("userEntity") UserEntity userEntity,
      @PathVariable("messageId") Integer messageId) {

  }

  @DeleteMapping("/reply/{messageReplyId}")
  public void deleteMessageReply(@RequestAttribute("userEntity") UserEntity userEntity,
      @PathVariable("messageReplyId") Integer messageReplyId) {

  }
}

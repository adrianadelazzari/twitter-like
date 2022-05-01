package org.ac.cst8277.deLazzari.adriana.web.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ac.cst8277.deLazzari.adriana.domain.entity.MessageEntity;
import org.ac.cst8277.deLazzari.adriana.domain.entity.MessageReplyEntity;
import org.ac.cst8277.deLazzari.adriana.domain.entity.UserEntity;
import org.ac.cst8277.deLazzari.adriana.domain.enumerator.RoleEnum;
import org.ac.cst8277.deLazzari.adriana.domain.valueObject.MessageVO;
import org.ac.cst8277.deLazzari.adriana.service.MessageLikeService;
import org.ac.cst8277.deLazzari.adriana.service.MessageReplyService;
import org.ac.cst8277.deLazzari.adriana.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "message")
@Tag(name = "message", description = "Message API")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successful operation"),
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(examples = {}))})
@RequiredArgsConstructor
public class MessageController {

  private final MessageService messageService;
  private final MessageReplyService messageReplyService;
  private final MessageLikeService messageLikeService;

  @PostMapping("/")
  public void publish(@RequestAttribute("userEntity") UserEntity userEntity,
      @RequestBody MessageVO messageVO) {
    boolean isProducer = userEntity.getRoleList().stream()
        .anyMatch(roleEntity -> RoleEnum.PRODUCER.getId().equals(roleEntity.getId()));
    if(isProducer){
      MessageEntity messageEntity = new MessageEntity();
      messageEntity.setContent(messageVO.getContent());
      messageEntity.setDate(Instant.now());
      messageEntity.setUserId(userEntity.getId());
      this.messageService.save(messageEntity);
    } else {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User must be producer.");
    }
  }

  @PostMapping("/{messageId}/reply")
  public void reply(@RequestAttribute("userEntity") UserEntity userEntity,
      @RequestBody MessageVO messageVO, @PathVariable("messageId") Integer messageId) {
    MessageReplyEntity messageReplyEntity = new MessageReplyEntity();
    messageReplyEntity.setContent(messageVO.getContent());
    messageReplyEntity.setDate(Instant.now());
    messageReplyEntity.setMessageId(messageId);
    messageReplyEntity.setUserId(userEntity.getId());
    this.messageReplyService.save(messageReplyEntity);
  }

  @PostMapping("/like")
  public void like(@RequestAttribute("userEntity") UserEntity userEntity,
      @RequestBody Integer messageId) {
    this.messageLikeService.like(messageId, userEntity.getId());
  }

  @GetMapping("/all")
  public List<MessageVO> getMessages(@RequestAttribute("userEntity") UserEntity userEntity) {
    List<MessageEntity> messageEntityList = this.messageService.getMessagesForUser(userEntity);
    List<MessageVO> messageVOList = new ArrayList<>();
    messageEntityList.forEach(messageEntity -> {
      MessageVO messageVO = new MessageVO();
      messageVO.setId(messageEntity.getId());
      messageVO.setContent(messageEntity.getContent());
      messageVO.setDate(messageEntity.getDate());
      messageVO.setUserId(messageEntity.getUserId());
      messageVOList.add(messageVO);
    });
    return messageVOList;
  }

  @DeleteMapping("/{messageId}")
  public void deleteMessage(@RequestAttribute("userEntity") UserEntity userEntity,
      @PathVariable("messageId") Integer messageId) {
    MessageEntity messageEntity = this.messageService.findById(messageId);
    if(messageEntity != null && messageEntity.getUserId().equals(userEntity.getId())){
      this.messageService.deleteById(messageId);
    } else{
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot delete message from another user.");
    }
  }

  @DeleteMapping("/reply/{messageReplyId}")
  public void deleteMessageReply(@RequestAttribute("userEntity") UserEntity userEntity,
      @PathVariable("messageReplyId") Integer messageReplyId) {
    MessageReplyEntity messageReplyEntity = this.messageReplyService.findById(messageReplyId);
    if(messageReplyEntity != null && messageReplyEntity.getUserId().equals(userEntity.getId())){
      this.messageReplyService.deleteById(messageReplyId);
    } else{
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot delete message reply from another user.");
    }
  }
}

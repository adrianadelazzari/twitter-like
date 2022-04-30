package org.ac.cst8277.deLazzari.adriana.web.controller;

import org.ac.cst8277.deLazzari.adriana.domain.valueObject.MessageVO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "message")
@Tag(name = "message", description = "Message API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(examples = {})) })
public class MessageController {

    @PostMapping("/")
    public void publish(@RequestBody MessageVO messageVO){

    }

    @PostMapping("/{messageId}/reply")
    public void reply(@RequestBody MessageVO messageVO, @PathVariable("messageId") Integer messageId){
        System.out.println(123);
    }

    @PostMapping("/like")
    public void like(@RequestBody Integer messageId){

    }

    @GetMapping("/all")
    public List<MessageVO> getMessages(){
        MessageVO temp = new MessageVO();
        return Collections.singletonList(temp);
    }

    @DeleteMapping("/{messageId}")
    public void deleteMessage(@PathVariable("messageId") Integer messageId){

    }

    @DeleteMapping("/reply/{messageReplyId}")
    public void deleteMessageReply(@PathVariable("messageReplyId") Integer messageReplyId){

    }
}

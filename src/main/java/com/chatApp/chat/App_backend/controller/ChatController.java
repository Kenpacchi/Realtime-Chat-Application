package com.chatApp.chat.App_backend.controller;

import com.chatApp.chat.App_backend.dto.MessageRequest;
import com.chatApp.chat.App_backend.entities.Message;
import com.chatApp.chat.App_backend.entities.Room;
import com.chatApp.chat.App_backend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Controller
public class ChatController {

    @Autowired
    RoomRepository roomRepository;

    @MessageMapping("/sendMessage/{roomId}")// /chat/sendMessage/{roomId}
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(@DestinationVariable String roomId, @RequestBody MessageRequest messagedata){
         Room room = roomRepository.findByRoomId(messagedata.getRoomId());
         Message message = new Message();
         message.setContent(messagedata.getContent());
         message.setSender(message.getSender());
         message.setTime(LocalDateTime.now());
         if(room != null){
             room.getMessageList().add(message);
             roomRepository.save(room);
         }else{
             throw new RuntimeException("Room Not Exist");
         }
         return message;
    }


}

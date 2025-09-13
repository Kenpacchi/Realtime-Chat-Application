package com.chatApp.chat.App_backend.controller;

import com.chatApp.chat.App_backend.entities.Room;
import com.chatApp.chat.App_backend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/chatApp")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    // Create Room
    @PostMapping("/chat")
    public ResponseEntity<?> sendMessage(@RequestParam String roomId) {

        if(roomRepository.findByRoomId(roomId)!=null){
            return ResponseEntity.badRequest().body("Room Already Exist");
        }
        // Creating new room
        Room room =new Room();
        room.setRoomId(roomId);
        roomRepository.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }
    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId){
        Room room = roomRepository.findByRoomId(roomId);
        if(room == null){
            return ResponseEntity.badRequest().body("Room Not Found By This Id");
        }
        return ResponseEntity.ok(room);
    }
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<?> getMessagesByRoomId(@PathVariable String roomId){
        Room room = roomRepository.findByRoomId(roomId);
        if(room == null){
            return ResponseEntity.badRequest().body("No Room Found By This Id");
        }
        return ResponseEntity.ok(room.getMessageList());
    }
}

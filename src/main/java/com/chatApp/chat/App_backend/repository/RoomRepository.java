package com.chatApp.chat.App_backend.repository;

import com.chatApp.chat.App_backend.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Room,String> {
    // get Room by roomId
    Room findByRoomId(String roomId);
}

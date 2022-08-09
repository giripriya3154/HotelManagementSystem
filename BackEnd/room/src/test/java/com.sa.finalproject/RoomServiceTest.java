package com.sa.finalproject;


import com.sa.finalproject.model.Room;
import com.sa.finalproject.service.RoomService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomServiceTest{

    @Autowired
    RoomService roomService;

    private static String createdRoomId = null;
    private static Room createdRoom = null;

    @Test
    public void createRoom() {
        Room room = new Room();
        room.setRoomNumber(606);
        room.setType("Single");
        room.setPrice(120.0);

        createdRoom = roomService.createRoom(room);
        createdRoomId = createdRoom.getRoomId();
        Assert.assertEquals(true,createdRoom.getRoomId()!=null);
    }


    @Test
    public void updateRoom(){
        createdRoom.setNumberOfBeds(2);
        createdRoom.setPrice(110.00);
        createdRoom.setSmoking(false);
        createdRoom =  roomService.updateRoom(createdRoomId,createdRoom);
        Assert.assertEquals(Optional.ofNullable(2), Optional.ofNullable(createdRoom.getNumberOfBeds()));
    }

    @Test
    public void findRoom(){

        Room retrived =  roomService.findById(createdRoomId);
        Assert.assertEquals(createdRoom,retrived);
    }


}

package com.example.sigaamobile;

import static org.junit.Assert.assertEquals;
import com.example.sigaamobile.models.mUser;
import org.junit.Test;

public class UserTest {
    private final mUser mUser = new mUser();

    @Test
    public void usernameTest(){

        mUser.setUsername("carlos_andre");
        assertEquals("carlos_andre", mUser.getUsername());
    }

    @Test
    public void passwordTest(){
        mUser.setPassword("85dsaiunf776");
        assertEquals("85dsaiunf776", mUser.getPassword());
    }

    @Test
    public void userIdTest(){
        mUser.setUserId(12);
        assertEquals(12, mUser.getUserId());
    }
}

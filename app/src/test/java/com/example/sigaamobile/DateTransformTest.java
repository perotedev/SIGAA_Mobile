package com.example.sigaamobile;

import static org.junit.Assert.assertEquals;
import com.example.sigaamobile.utils.DateTransform;
import org.junit.Test;

public class DateTransformTest {

    @Test
    public void secondsToStringTest(){
        int timestamp = 1660093818;
        String stringDate = DateTransform.transformToStringDate(timestamp, DateTransform.SECONDS);
        assertEquals("09/08/2022", stringDate);
    }

    @Test
    public void milissecondsToStringTest(){
        long timestamp = 1660093818000L;
        String stringDate = DateTransform.transformToStringDate(timestamp, DateTransform.MILISSECONDS);
        assertEquals("09/08/2022", stringDate);
    }

    @Test (expected = RuntimeException.class)
    public void invalidTimestampTest(){
        int timestamp = -1;
        DateTransform.transformToStringDate(timestamp, DateTransform.SECONDS);
    }

    @Test (expected = RuntimeException.class)
    public void invalidTypeTest(){
        long timestamp = 1660093818000L;
        DateTransform.transformToStringDate(timestamp, 10);
    }
}